package ro.uvt.info.designpatternslab2026.books;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uvt.info.designpatternslab2026.Book;
import ro.uvt.info.designpatternslab2026.command.*;
import ro.uvt.info.designpatternslab2026.observer.AllBooksSubject;
import ro.uvt.info.designpatternslab2026.persistence.CrudRepository;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/** Presentation layer: builds commands and hands them to the right executor (the invoker). */
@RestController
@RequestMapping("/books")
public class BooksController {
    private final CrudRepository<Book, Integer> booksRepository;
    private final IsbnService isbnService;
    private final CommandExecutor syncExecutor;
    private final CommandExecutor asyncExecutor;
    private final TaskRegistry taskRegistry;
    private final AllBooksSubject allBooksSubject;

    public BooksController(CrudRepository<Book, Integer> booksRepository,
                           IsbnService isbnService,
                           SynchronousCommandExecutor syncExecutor,
                           AsynchronousCommandExecutor asyncExecutor,
                           TaskRegistry taskRegistry,
                           AllBooksSubject allBooksSubject) {
        this.booksRepository = booksRepository;
        this.isbnService = isbnService;
        this.syncExecutor = syncExecutor;
        this.asyncExecutor = asyncExecutor;
        this.taskRegistry = taskRegistry;
        this.allBooksSubject = allBooksSubject;
    }

    @GetMapping
    public List<Book> getAll() {
        return await(syncExecutor.submit(new GetAllBooksCommand(booksRepository)));
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Integer id) {
        return await(syncExecutor.submit(new GetBookCommand(booksRepository, id)));
    }

    /** Asynchronous: depends on a slow external ISBN service. Responds 202 + status location. */
    @PostMapping
    public ResponseEntity<TaskStatus> create(@RequestBody BookRequest request) {
        CompletableFuture<Book> future =
                asyncExecutor.submit(new CreateBookCommand(booksRepository, isbnService, request.toBook()));
        // once the book is saved, change the subject's state -> observers get notified
        future.thenAccept(allBooksSubject::add);
        String taskId = taskRegistry.register(future);
        return ResponseEntity.accepted()
                .location(URI.create("/books/tasks/" + taskId))
                .body(new TaskStatus(taskId, "PENDING", null, null));
    }

    @GetMapping("/tasks/{taskId}")
    public TaskStatus taskStatus(@PathVariable String taskId) {
        try {
            return taskRegistry.status(taskId);
        } catch (NoSuchElementException e) {
            throw new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Book replace(@PathVariable Integer id, @RequestBody BookRequest request) {
        return await(syncExecutor.submit(new ReplaceBookCommand(booksRepository, id, request.toBook())));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        await(syncExecutor.submit(new DeleteBookCommand(booksRepository, id)));
    }

    private static <T> T await(CompletableFuture<T> future) {
        try {
            return future.join();
        } catch (CompletionException e) {
            if (e.getCause() instanceof RuntimeException re) throw re;
            throw e;
        }
    }
}
