package dev.titans.services;

import dev.titans.entities.Behavior;
import dev.titans.entities.Grade;
import dev.titans.exceptions.GradeNotFoundException;
import dev.titans.exceptions.StudentNotFoundException;
import dev.titans.repos.GradeRepo;
import dev.titans.repos.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService{

    @Autowired
    GradeRepo gradeRepo;
    @Autowired
    StudentRepo studentRepo;

    @Override
    public Grade addGrade(Grade grade) {
       Grade savedGrade = this.gradeRepo.save(grade);
       return savedGrade;
    }

    @Override
    public List<Grade> getGradesByStudentId(int id) {
        if(this.studentRepo.existsById(id)){
            return this.gradeRepo.findByStudentId(id);
        }else{
            throw new StudentNotFoundException();
        }
    }

    @Override
    public void deleteGradeById(int id) {
        if(this.gradeRepo.existsById(id)){
            this.gradeRepo.deleteById(id);
        }else{
            throw new GradeNotFoundException();
        }
    }
}
