package ro.uvt.info.designpatternslab2026.command;

import ro.uvt.info.designpatternslab2026.Book;
import ro.uvt.info.designpatternslab2026.books.BookNotFoundException;
import ro.uvt.info.designpatternslab2026.persistence.CrudRepository;

public class GetBookCommand implements Command<Book> {
    private final CrudRepository<Book, Integer> repository;
    private final Integer id;

    public GetBookCommand(CrudRepository<Book, Integer> repository, Integer id) {
        this.repository = repository;
        this.id = id;
    }

    @Override
    public Book execute() {
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }
}
