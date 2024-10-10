package sarik.dev.springbootwithjpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Talaba topilmagan holatlar uchun maxsus xato sinfi.
 * Ushbu xato tashlanganda 404 NOT FOUND javob holati qaytariladi.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException {
    /**
     * Xato xabari.
     *
     * @param message Xato haqida xabar.
     */
    public StudentNotFoundException(String message) {
        super(message);
    }
}
