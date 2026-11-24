package ro.uvt.info.designpatternslab2026.persistence;

import java.util.List;
import java.util.Optional;

/** Persistence abstraction used by upper layers; independent of Spring Data. */
public interface CrudRepository<T, ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
    boolean existsById(ID id);
    T save(T entity);
    void deleteById(ID id);
}
