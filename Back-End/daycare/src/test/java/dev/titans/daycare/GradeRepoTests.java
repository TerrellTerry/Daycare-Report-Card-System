package dev.titans.daycare;

import dev.titans.entities.Behavior;
import dev.titans.entities.Grade;



import dev.titans.entities.Student;
import dev.titans.repos.GradeRepo;
import dev.titans.repos.StudentRepo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class GradeRepoTests {

    @Autowired
    GradeRepo gradeRepo;
    @Autowired
    StudentRepo studentRepo;


    @Test
    public void create_grades() {
        // need a student in the DB in order to create a grade
        Student student = new Student(0,"Beast","Boy","Batman");
        Student savedStudent = studentRepo.save(student);
        Grade grade = new Grade(0, savedStudent.getStudentId(), 0, "Shapeshifting during naptime", Behavior.MISBEHAVED);
        Grade savedGrade = this.gradeRepo.save(grade);
        System.out.println(savedGrade);
        Assertions.assertNotEquals(0, savedGrade.getGradeId());
    }

    @Test
    public void get_all_grades_test() {
        List<Grade> grades = this.gradeRepo.findAll();
        System.out.print(grades);
    }


    @Test
    public void delete_grade(){
        // need a student in the DB in order to create a grade
        Student student = new Student(0,"Beast","Boy","Batman");
        Student savedStudent = studentRepo.save(student);
        // need to save a grade in the DB to delete it
        Grade grade = new Grade(0,savedStudent.getStudentId(),0,"Beast boy behaved well today!", Behavior.RESPONSIBLE);
        Grade savedGrade = gradeRepo.save(grade);

        gradeRepo.deleteById(savedGrade.getGradeId());
        // will attempt to retrieve the deleted grade, and expect nothing to be there, throwing an error
        Assertions.assertThrows(JpaObjectRetrievalFailureException.class,()->{
            Grade deletedGrade = gradeRepo.getReferenceById(savedGrade.getGradeId());
        });
    }

    @Test
    public void get_grade_by_s_id(){
        // need a student in the DB in order to create a grade
        Student student = new Student(0,"Beast","Boy","Batman");
        Student savedStudent = studentRepo.save(student);
        // need to save a grade in the DB
        Grade grade = new Grade(0,savedStudent.getStudentId(),0,"Beast boy behaved well today!", Behavior.RESPONSIBLE);
        Grade savedGrade = gradeRepo.save(grade);

        List<Grade> grades = gradeRepo.findByStudentId(savedStudent.getStudentId());

        Assertions.assertEquals(1, grades.size());
    }
}

