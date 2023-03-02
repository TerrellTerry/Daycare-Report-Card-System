package dev.titans.daycare;

import dev.titans.entities.Behavior;
import dev.titans.entities.Grade;
import dev.titans.entities.Student;
import dev.titans.exceptions.GradeNotFoundException;
import dev.titans.repos.GradeRepo;
import dev.titans.repos.StudentRepo;
import dev.titans.services.GradeService;
import dev.titans.services.GradeServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class GradeServiceTests {

    @InjectMocks
    GradeServiceImpl gradeService;

    @Mock
    GradeRepo gradeRepo;

    @Mock
    StudentRepo studentRepo;

    private Student genericStudent;

    private Grade grade1;
    private Grade grade2;

    private List<Grade> grades;


    @BeforeEach
    void setup(){
        genericStudent = new Student(32,"Beast","Boy","Batman");
        grade1 = new Grade(0,32,0,"Beast boy behaved well today!", Behavior.RESPONSIBLE);
        grade2 = new Grade(1,32,0,"Beast boy behaved well today!", Behavior.RESPONSIBLE);

        grades = new ArrayList<>();

        grades.add(grade1);
        grades.add(grade2);
    }

    //Checks to make sure the grade is getting deleted
    @Test
    void delete_grade_by_id(){
        Mockito.when(gradeRepo.existsById(grade1.getGradeId())).thenReturn(false);

        Assertions.assertThrows(GradeNotFoundException.class, () ->{
            gradeService.deleteGradeById(grade1.getGradeId());
        });
    }

    @Test
    void get_grades_by_student_id(){
        //Mockito.when(gradeService.getGradesByStudentId(genericStudent.getStudentId())).thenReturn(grades);

        Mockito.when(studentRepo.existsById(genericStudent.getStudentId())).thenReturn(true);
        Mockito.when(gradeRepo.findByStudentId(genericStudent.getStudentId())).thenReturn(grades);

        System.out.println(grades.size() + " " + gradeService.getGradesByStudentId(genericStudent.getStudentId()).size());

        Assertions.assertEquals(grades.size(), gradeService.getGradesByStudentId(genericStudent.getStudentId()).size());


    }
}
