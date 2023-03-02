package dev.titans.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.titans.entities.Student;
import dev.titans.exceptions.InsufficientPermissionException;
import dev.titans.exceptions.UnauthenticatedException;
import dev.titans.services.JwtValidationService;
import dev.titans.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@CrossOrigin
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    JwtValidationService jwtValidationService;

    @Autowired
    JmsTemplate jmsTemplate;

    @DeleteMapping("/students/{id}")
    public void deleteStudentById(@RequestHeader("auth") String jwt,@PathVariable String id){
        if(jwtValidationService.validateJwt(jwt)){
            DecodedJWT decodedJWT = JWT.decode(jwt);
            String role = decodedJWT.getClaim("role").asString();

            if(role.equals("Teacher")){
                int s_id = Integer.parseInt(id);
                String name = this.studentService.deleteStudentById(s_id);
                if(!name.equals("")){
                    String message = "Student with name:"+ name +" was deleted from the class";
                    jmsTemplate.convertAndSend("titan-important-events", message);
                }
                return;
            }else{
                throw new InsufficientPermissionException();
            }
        }
        throw new UnauthenticatedException();
    }

    @PostMapping("/students")
    public Student createStudent(@RequestHeader("auth") String jwt,@RequestBody Student student){

        if(jwtValidationService.validateJwt(jwt)){
            DecodedJWT decodedJWT = JWT.decode(jwt);
            String role = decodedJWT.getClaim("role").asString();

            if(role.equals("Teacher")){
                String name = student.getFirstName() + " " + student.getLastName();
                String message = "Student with name:"+ name +" was added to the class";
                jmsTemplate.convertAndSend("titan-important-events", message);
                return this.studentService.createStudent(student);
            }else{
                throw new InsufficientPermissionException();
            }
        }
        throw new UnauthenticatedException();
    }

    @GetMapping("/students")
    public List<Student> getStudentsByName(@RequestHeader("auth") String jwt,@RequestParam(required = false) String name){

        if(jwtValidationService.validateJwt(jwt)){
            DecodedJWT decodedJWT = JWT.decode(jwt);
            String role = decodedJWT.getClaim("role").asString();

            if(role.equals("Teacher")){
                if(name == null){
                    return this.studentService.getStudents();
                }else{
                    return this.studentService.getStudentsByName(name);
                }
            }else if(role.equals("Guardian")){
                String username = decodedJWT.getClaim("username").asString();
                return this.studentService.getStudentsByGuardianUsername(username);
            }
        }
        throw new UnauthenticatedException();
    }
}
