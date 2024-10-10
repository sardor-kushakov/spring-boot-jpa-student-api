package sarik.dev.springbootwithjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootWithJpaApplication - Spring Boot ilovasi uchun asosiy klass.
 * Ushbu klass Spring Boot ilovasini ishga tushirish va konfiguratsiya qilish uchun
 * javobgar.
 *
 * @author SARDOR KUSHAKOV
 * @version 1.0.0
 * @see <a href="https://github.com/sardor-kushakov/spring-boot-jpa-student-api">GitHub repository</a>
 */
@SpringBootApplication
public class SpringBootWithJpaApplication {
    public static void main(String[] args) {
        // Spring Boot ilovasini ishga tushirish
        SpringApplication.run(SpringBootWithJpaApplication.class, args);
    }
}
