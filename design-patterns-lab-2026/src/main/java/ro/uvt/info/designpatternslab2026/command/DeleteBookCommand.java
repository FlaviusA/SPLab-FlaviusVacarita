package ro.uvt.info.designpatternslab2026.command;

import ro.uvt.info.designpatternslab2026.Book;
import ro.uvt.info.designpatternslab2026.books.BookNotFoundException;
import ro.uvt.info.designpatternslab2026.persistence.CrudRepository;

public class DeleteBookCommand implements Command<Void> {
    private final CrudRepository<Book, Integer> repository;
    private final Integer id;

    public DeleteBookCommand(CrudRepository<Book, Integer> repository, Integer id) {
        this.repository = repository;
        this.id = id;
    }

    @Override
    public Void execute() {
        if (!repository.existsById(id)) throw new BookNotFoundException(id);
        repository.deleteById(id);
        return null;
    }
}
