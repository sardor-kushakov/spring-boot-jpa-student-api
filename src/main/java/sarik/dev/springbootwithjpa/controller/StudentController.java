package sarik.dev.springbootwithjpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sarik.dev.springbootwithjpa.exception.StudentNotFoundException;
import sarik.dev.springbootwithjpa.model.Student;
import sarik.dev.springbootwithjpa.repository.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Yangi talabani qo'shish.
     * Ushbu metod HTTP POST so'rovi orqali talabani ob'ekti qabul qiladi va uni ma'lumotlar bazasiga saqlaydi.
     *
     * @param student Talaba haqida ma'lumotlar (ism, familiya, telefon raqami).
     * @return Talaba muvaffaqiyatli qo'shilgani haqida xabar.
     */
    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student successfully added!");
    }

    /**
     * Barcha talabalarni olish.
     * Ushbu metod HTTP GET so'rovi orqali barcha talabalar ro'yxatini qaytaradi.
     *
     * @return Barcha talabalar ro'yxati.
     */
    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    /**
     * Talabani ID bo'yicha olish.
     * Ushbu metod berilgan ID orqali talabani ma'lumotlar bazasidan olish uchun ishlatiladi.
     *
     * @param id Talabaning noyob identifikatori.
     * @return Talaba ma'lumotlari yoki agar talaba topilmasa xato xabari.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        return studentRepository.findById(id)
                .map(student -> ResponseEntity.ok().body(student))
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + id));
    }

    /**
     * Talabani ID bo'yicha o'chirish.
     * Ushbu metod berilgan ID orqali talabani ma'lumotlar bazasidan o'chiradi.
     *
     * @param id Talabaning noyob identifikatori.
     * @return Talaba muvaffaqiyatli o'chirilgani haqida xabar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Integer id) {
        studentRepository.deleteById(id);
        return ResponseEntity.ok("Student successfully deleted!");
    }

    /**
     * Talabani ID bo'yicha yangilash.
     * Ushbu metod berilgan ID orqali talabani ma'lumotlari yangilanadi.
     *
     * @param id Talabaning noyob identifikatori.
     * @param student Yangilangan talaba ma'lumotlari.
     * @return Talaba muvaffaqiyatli yangilanganligi haqida xabar, yoki agar talaba topilmasa xato.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setFirstName(student.getFirstName());
                    existingStudent.setLastName(student.getLastName());
                    existingStudent.setPhoneNumber(student.getPhoneNumber());
                    studentRepository.save(existingStudent);
                    return ResponseEntity.ok("Student successfully updated!");
                })
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + id));
    }

    /**
     * Talabani ID bo'yicha topish yoki xato tashlash.
     * Ushbu metod ichki ravishda talaba ID bo'yicha topiladi yoki xato tashlaydi agar topilmasa.
     *
     * @param id Talabaning noyob identifikatori.
     * @return Talaba ma'lumotlari yoki xato.
     */
    private Student findStudentByIdOrThrow(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + id));
    }
}
