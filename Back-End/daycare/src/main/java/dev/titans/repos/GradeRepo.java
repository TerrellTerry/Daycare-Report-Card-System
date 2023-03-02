package dev.titans.repos;

import dev.titans.entities.Behavior;
import dev.titans.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepo extends JpaRepository<Grade,Integer>{
    List<Grade> findByStudentId(int id);
}
