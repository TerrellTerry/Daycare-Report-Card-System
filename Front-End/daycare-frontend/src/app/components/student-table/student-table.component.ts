import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from 'src/app/models/student';
import { JwtDecoderService } from 'src/app/services/jwt-decoder.service';
import { StudentIdDataService } from 'src/app/services/student-id-data.service';
import { StudentUtilServiceService } from 'src/app/services/student-util-service.service';

@Component({
  selector: 'app-student-table',
  templateUrl: './student-table.component.html',
  styleUrls: ['./student-table.component.css']
})
export class StudentTableComponent implements OnInit {

  constructor(private studentService:StudentUtilServiceService, private studentIdData:StudentIdDataService, private router:Router, private jwtDecoder:JwtDecoderService) { }

  studentName:string = "";

  students:Student[] = [];

  role:string = this.jwtDecoder.parseJwt().role;

  ngOnInit(): void {
    this.getAllStudents();
  }

  getStudentsWithName(){
    if(this.studentName != ""){
      (async () =>{
        this.students = await this.studentService.getStudentByName(this.studentName);
      })();
    }
    else{
      this.getAllStudents();
    }

  }

  getAllStudents(){
    (async () =>{
      this.students = await this.studentService.getAllStudents();
    })();
  }

  viewGrades(id:number){
    this.studentIdData.studentId = id;
    // load the page for grades
    this.router.navigateByUrl("/gradepage");
  }

  deleteStudent(id:number){
    // Add a pop-up that asks if they're sure.
    const confirm = window.confirm("Are you sure you would like to delete this student?\nPress OK to delete.");
    if(confirm){
      //Delete the student
      this.studentService.deleteStudent(id);
      window.location.reload();
    }
  }

}
