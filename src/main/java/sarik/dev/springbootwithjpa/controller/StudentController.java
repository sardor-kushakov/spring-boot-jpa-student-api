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
     * Add a new student.
     * This method accepts a student object through an HTTP POST request and saves it to the database.
     *
     * @param student The student details (first name, last name, phone number).
     * @return A message indicating that the student was successfully added.
     */
    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student successfully added!");
    }

    /**
     * Retrieve all students.
     * This method returns a list of all students in response to an HTTP GET request.
     *
     * @return A list of all students.
     */
    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    /**
     * Retrieve a student by their ID.
     * This method fetches the student with the given ID from the database in response to an HTTP GET request.
     *
     * @param id The unique identifier of the student.
     * @return The student details or an error message if the student is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        return studentRepository.findById(id)
                .map(student -> ResponseEntity.ok().body(student))
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + id));
    }

    /**
     * Delete a student by their ID.
     * This method deletes the student with the given ID from the database in response to an HTTP DELETE request.
     *
     * @param id The unique identifier of the student.
     * @return A message indicating that the student was successfully deleted.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Integer id) {
        studentRepository.deleteById(id);
        return ResponseEntity.ok("Student successfully deleted!");
    }

    /**
     * Update a student by their ID.
     * This method updates the details of the student with the given ID in response to an HTTP PUT request.
     *
     * @param id The unique identifier of the student.
     * @param student The updated student details.
     * @return A message indicating that the student was successfully updated, or an error if the student is not found.
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
     * Helper method to find a student by ID or throw an exception if not found.
     * This method is used internally to retrieve a student by ID, or throw an error if the student is not found.
     *
     * @param id The unique identifier of the student.
     * @return The student details or an exception.
     */
    private Student findStudentByIdOrThrow(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + id));
    }
}
