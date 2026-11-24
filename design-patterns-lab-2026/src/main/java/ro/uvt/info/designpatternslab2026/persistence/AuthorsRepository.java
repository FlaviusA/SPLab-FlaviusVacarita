package ro.uvt.info.designpatternslab2026.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.uvt.info.designpatternslab2026.Author;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Integer> {
}
