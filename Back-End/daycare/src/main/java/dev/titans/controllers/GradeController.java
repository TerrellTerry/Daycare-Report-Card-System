package dev.titans.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.titans.entities.Grade;
import dev.titans.entities.Student;
import dev.titans.exceptions.InsufficientPermissionException;
import dev.titans.exceptions.UnauthenticatedException;
import dev.titans.services.GradeService;
import dev.titans.services.JwtValidationService;
import dev.titans.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RestController
@CrossOrigin
public class GradeController {

    @Autowired
    StudentService studentService;

    @Autowired
    GradeService gradeService;

    @Autowired
    JwtValidationService jwtValidationService;

    @PostMapping("/grades")
    public ResponseEntity<Grade> createGrade(@RequestHeader("auth") String jwt,@RequestBody Grade body) {
        if(jwtValidationService.validateJwt(jwt)){
            DecodedJWT decodedJWT = JWT.decode(jwt);
            String role = decodedJWT.getClaim("role").asString();

            if(role.equals("Teacher")){
                Grade savedGrade = this.gradeService.addGrade(body);
                return new ResponseEntity<Grade>(savedGrade, HttpStatus.CREATED);
            }else{
                throw new InsufficientPermissionException();
            }
        }
        throw new UnauthenticatedException();
    }

    @GetMapping("/students/{id}/grades")
    public List<Grade> getGradesByStudentId(@RequestHeader("auth") String jwt,@PathVariable String id){
        if(jwtValidationService.validateJwt(jwt)){
            DecodedJWT decodedJWT = JWT.decode(jwt);
            String role = decodedJWT.getClaim("role").asString();
            int studentId = Integer.parseInt(id);

            if(role.equals("Teacher")){
                return this.gradeService.getGradesByStudentId(studentId);

            }else if(role.equals("Guardian")){
                String username = decodedJWT.getClaim("username").asString();
                List<Student> students = this.studentService.getStudentsByGuardianUsername(username);
                for(Student student: students){
                    if(student.getStudentId() == studentId){
                        return this.gradeService.getGradesByStudentId(studentId);
                    }
                }
            }
        }
        throw new UnauthenticatedException();
    }

    @DeleteMapping("/grades/{id}")
    void deleteGradeById(@RequestHeader("auth") String jwt,@PathVariable String id){
        if(jwtValidationService.validateJwt(jwt)){
            DecodedJWT decodedJWT = JWT.decode(jwt);
            String role = decodedJWT.getClaim("role").asString();

            if(role.equals("Teacher")){
                int g_id = Integer.parseInt(id);
                this.gradeService.deleteGradeById(g_id);
            }else{
                throw new InsufficientPermissionException();
            }
        }
        throw new UnauthenticatedException();
    }
}

