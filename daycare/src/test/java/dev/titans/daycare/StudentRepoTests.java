package dev.titans.daycare;

import dev.titans.entities.Student;
import dev.titans.repos.StudentRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class StudentRepoTests {

    @Autowired
    StudentRepo studentRepo;

    @Test
    void create_student(){
        Student student = new Student(0,"Beast","Boy","Batman");
        Student savedStudent = studentRepo.save(student);
        Assertions.assertNotEquals(0,savedStudent.getStudentId());
    }

    @Test
    void delete_student(){
        Student savedStudent = studentRepo.save(new Student(0, "Burger", "Man", "Chkechn"));
        int id = savedStudent.getStudentId();
        studentRepo.deleteById(id);
        Assertions.assertFalse(studentRepo.existsById(id));

    }

    @Test
    void get_student_by_name(){
        Student savedStudent1 = studentRepo.save(new Student(0, "Burger", "Man", "Chkechn"));
        Student savedStudent2 = studentRepo.save(new Student(0, "Man", "Burger", "Chkechn"));
        Student savedStudent3 = studentRepo.save(new Student(0, "Burger", "Guy", "Chkechn"));
        List<Student> students = studentRepo.findByFirstNameOrLastName("Burger","Burger");
        System.out.println(students);
        Assertions.assertTrue(students.size()>=3);
    }

    @Test
    void get_all_students(){
        Student savedStudent = studentRepo.save(new Student(0, "Burger", "Man", "Chkechn"));
        List<Student> students = studentRepo.findAll();
        Assertions.assertNotEquals(0,students.size());
    }

    @Test
    void get_by_guardian_username(){
        Student savedStudent = studentRepo.save(new Student(0, "Burger", "Man", "Chkechn"));
        List<Student> students = studentRepo.findByGuardianUsername("Chkechn");
        Assertions.assertNotEquals(0,students.size());
    }
}
