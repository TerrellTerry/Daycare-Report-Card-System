package dev.titans.services;

import dev.titans.entities.Student;

import java.util.List;

public interface StudentService {
    String deleteStudentById(int id);
    Student createStudent(Student student);
    List<Student> getStudentsByName(String name);
    List<Student> getStudents();
    List<Student> getStudentsByGuardianUsername(String username);
}
