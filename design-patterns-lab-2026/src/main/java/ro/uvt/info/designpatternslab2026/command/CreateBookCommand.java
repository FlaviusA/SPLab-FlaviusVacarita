package ro.uvt.info.designpatternslab2026.command;

import ro.uvt.info.designpatternslab2026.Book;
import ro.uvt.info.designpatternslab2026.books.BookNotFoundException;
import ro.uvt.info.designpatternslab2026.books.IsbnService;
import ro.uvt.info.designpatternslab2026.persistence.CrudRepository;

/** Orchestrates two collaborators: obtain an ISBN externally, then persist the book. */
public class CreateBookCommand implements Command<Book> {
    private final CrudRepository<Book, Integer> repository;
    private final IsbnService isbnService;
    private final Book book;

    public CreateBookCommand(CrudRepository<Book, Integer> repository, IsbnService isbnService, Book book) {
        this.repository = repository;
        this.isbnService = isbnService;
        this.book = book;
    }

    @Override
    public Book execute() {
        book.setIsbn(isbnService.obtainIsbn());
        return repository.save(book);
    }
}
