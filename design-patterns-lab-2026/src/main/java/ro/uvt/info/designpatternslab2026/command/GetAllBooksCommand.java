package ro.uvt.info.designpatternslab2026.command;

import ro.uvt.info.designpatternslab2026.Book;
import ro.uvt.info.designpatternslab2026.books.BookNotFoundException;
import ro.uvt.info.designpatternslab2026.persistence.CrudRepository;

import java.util.List;

public class GetAllBooksCommand implements Command<List<Book>> {
    private final CrudRepository<Book, Integer> repository;

    public GetAllBooksCommand(CrudRepository<Book, Integer> repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> execute() {
        return repository.findAll();
    }
}
