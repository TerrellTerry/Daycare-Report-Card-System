package dev.titans.daycare;

import dev.titans.entities.Behavior;
import dev.titans.entities.Grade;
import dev.titans.entities.Student;
import dev.titans.exceptions.GradeNotFoundException;
import dev.titans.exceptions.StudentNotFoundException;
import dev.titans.repos.StudentRepo;
import dev.titans.services.StudentService;
import dev.titans.services.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class StudentServiceTests {

    @InjectMocks
    StudentServiceImpl studentService;

    @Mock
    StudentRepo studentRepo;

    private Student genericStudent;

    @BeforeEach
    void setup(){
        genericStudent = new Student(32,"Beast","Boy","Batman");
    }

    @Test
    void delete_student_by_id(){
        Mockito.when(studentRepo.existsById(genericStudent.getStudentId())).thenReturn(false);

        Assertions.assertThrows(StudentNotFoundException.class,()->{
            studentService.deleteStudentById(genericStudent.getStudentId());
        });
    }
}
