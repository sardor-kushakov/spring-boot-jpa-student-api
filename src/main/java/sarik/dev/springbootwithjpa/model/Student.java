package sarik.dev.springbootwithjpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Student klassi - talaba ma'lumotlarini saqlash uchun mo'ljallangan model.
 * Bu klass "students" jadvaliga mos keladi va talabaning identifikatori,
 * ismi, familiyasi va telefon raqamini o'z ichiga oladi.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Talabaning yagona identifikatori

    @Column(nullable = false)
    private String firstName; // Talabaning ismi

    @Column(nullable = false)
    private String lastName; // Talabaning familiyasi

    @Column(nullable = false, unique = true)
    private String phoneNumber; // Talabaning telefon raqami
}
