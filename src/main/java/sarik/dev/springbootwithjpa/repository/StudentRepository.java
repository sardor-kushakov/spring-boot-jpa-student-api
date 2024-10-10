package sarik.dev.springbootwithjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.springbootwithjpa.model.Student;

/**
 * Student entitysi uchun repository interfeysi.
 * JPA yordamida Talabalar uchun CRUD (yaratish, o'qish, yangilash, o'chirish) operatsiyalarini taqdim etadi.
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    /* Talabalar bilan bog'liq bazaviy operatsiyalarni amalga oshirish uchun!
     Spring Data JPA tomonidan avtomatik ravishda taqdim etilgan metodlar mavjud.*/
}
