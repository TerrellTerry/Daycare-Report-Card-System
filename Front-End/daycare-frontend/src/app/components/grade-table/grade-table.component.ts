import { Time } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Grade } from 'src/app/models/grade';
import { GradeUtilServiceService } from 'src/app/services/grade-util-service.service';
import { JwtDecoderService } from 'src/app/services/jwt-decoder.service';
import { StudentIdDataService } from 'src/app/services/student-id-data.service';

@Component({
  selector: 'app-grade-table',
  templateUrl: './grade-table.component.html',
  styleUrls: ['./grade-table.component.css']
})
export class GradeTableComponent implements OnInit {

  constructor(private gradeService:GradeUtilServiceService, private studentIdData:StudentIdDataService, private router:Router, private jwtDecoder:JwtDecoderService) { }
  grades:Grade[] = [];
  studentId:number = this.studentIdData.studentId;
  note:string="";
  behavior:string = "";
  newId = 0;

  role:string = this.jwtDecoder.parseJwt().role;

  ngOnInit(): void {
      this.getGradeByStudentId();
  }
  async createGrade() {
    if(this.note !== "" && this.behavior !== "")
    {
      const grade:Grade = {gradeId:0, studentId:this.studentId, note:this.note, timeReported:Math.round(Date.now() / 1000)
      , behavior:this.behavior};
      const newGrade:Grade = await this.gradeService.createGrade(grade);
      this.newId = newGrade.gradeId;
      
      //Alert
      //Needs to check the status of the HTTP request before the alert.
      alert("Created a grade for the student");
      this.getGradeByStudentId();
    }
  }

  async getGradeByStudentId() {
    if(this.studentId != 0) {
        this.grades = await this.gradeService.getGradesByStudentId(this.studentId);
    }
    
  }

  deleteGrade(id:number) {
    const confirm = window.confirm("Are you sure you want to delete this grade?\nPress OK to delete.")
    if(confirm) {
      this.gradeService.deleteGradeById(id);
      alert("Deleted the grade from the student");
      this.getGradeByStudentId();
    }
  }


 
}
