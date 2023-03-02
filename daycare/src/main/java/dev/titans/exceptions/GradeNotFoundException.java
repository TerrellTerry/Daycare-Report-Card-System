package dev.titans.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Grade with ID was not found.")
public class GradeNotFoundException extends RuntimeException{
}
