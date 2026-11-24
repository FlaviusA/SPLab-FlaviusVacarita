package ro.uvt.info.designpatternslab2026.command;

import ro.uvt.info.designpatternslab2026.Book;
import ro.uvt.info.designpatternslab2026.books.BookNotFoundException;
import ro.uvt.info.designpatternslab2026.persistence.CrudRepository;

public class ReplaceBookCommand implements Command<Book> {
    private final CrudRepository<Book, Integer> repository;
    private final Integer id;
    private final Book replacement;

    public ReplaceBookCommand(CrudRepository<Book, Integer> repository, Integer id, Book replacement) {
        this.repository = repository;
        this.id = id;
        this.replacement = replacement;
    }

    @Override
    public Book execute() {
        Book existing = repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        existing.replaceWith(replacement);
        return repository.save(existing);
    }
}
