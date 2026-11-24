package ro.uvt.info.designpatternslab2026.persistence;

import org.springframework.stereotype.Component;
import ro.uvt.info.designpatternslab2026.Book;

import java.util.List;
import java.util.Optional;

/** Adapter: Spring Data's BooksRepository -> our CrudRepository interface. */
@Component
public class BooksRepositoryAdapter implements CrudRepository<Book, Integer> {
    private final BooksRepository repository;

    public BooksRepositoryAdapter(BooksRepository repository) {
        this.repository = repository;
    }

    @Override public List<Book> findAll() { return repository.findAll(); }
    @Override public Optional<Book> findById(Integer id) { return repository.findById(id); }
    @Override public boolean existsById(Integer id) { return repository.existsById(id); }
    @Override public Book save(Book book) { return repository.save(book); }
    @Override public void deleteById(Integer id) { repository.deleteById(id); }
}
