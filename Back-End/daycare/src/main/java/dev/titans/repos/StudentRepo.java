package dev.titans.repos;

import dev.titans.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {
    List<Student> findByFirstNameOrLastName(String firstName, String lastName);
    List<Student> findByGuardianUsername(String username);
}
