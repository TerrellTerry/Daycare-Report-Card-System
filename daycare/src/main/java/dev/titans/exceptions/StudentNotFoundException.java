package dev.titans.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Student with ID was not found.")
public class StudentNotFoundException extends RuntimeException{
}
