package sarik.dev.springbootwithjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.springbootwithjpa.model.Student;

/**
 * Repository interface for Student entity.
 * Provides CRUD operations for Student using JPA.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
