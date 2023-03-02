import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from 'src/app/models/student';
import { StudentUtilServiceService } from 'src/app/services/student-util-service.service';

@Component({
  selector: 'app-create-student-form',
  templateUrl: './create-student-form.component.html',
  styleUrls: ['./create-student-form.component.css']
})
export class CreateStudentFormComponent implements OnInit {

  constructor(private studentService:StudentUtilServiceService, private router:Router) { }

  ngOnInit(): void {
  }

  firstName:string = '';
  lastName:string = '';
  guardianUsername:string = '';

  savedId = 0;
  async registerStudent(){

    const student:Student = {studentId:0,firstName:this.firstName,lastName:this.lastName,guardianUsername:this.guardianUsername};
    const savedStudent:Student = await this.studentService.createStudent(student);
    this.savedId = savedStudent.studentId;
    this.router.navigateByUrl("home")
  }
}
