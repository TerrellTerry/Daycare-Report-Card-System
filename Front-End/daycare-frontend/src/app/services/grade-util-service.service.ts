import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom, observable } from 'rxjs';
import { Grade } from '../models/grade';

@Injectable({
  providedIn: 'root'
})
export class GradeUtilServiceService {

  constructor(private http:HttpClient) { }

  authString = String(localStorage.getItem(`userInfo`)).replace(/"/g, "");

  async createGrade(grade:Grade):Promise<Grade>{
    const headers = new HttpHeaders().set('auth', this.authString).set('Content-Type', 'application/json');
    const observable = this.http.post<Grade>("https://daycare.salmoncliff-9cdcd619.centralus.azurecontainerapps.io/grades", grade, {headers: headers});
    const savedGrade = firstValueFrom(observable);
    return savedGrade;
  }

  async getGradesByStudentId(id:number):Promise<Grade[]>{
    const headers = new HttpHeaders().set('auth', this.authString).set('Content-Type', 'application/json');
    const observable = this.http.get<Grade[]>(`https://daycare.salmoncliff-9cdcd619.centralus.azurecontainerapps.io/students/${id}/grades`, {headers: headers});
    const grades = firstValueFrom(observable);
    return grades;
  }

  async deleteGradeById(id:number){
    const headers = new HttpHeaders().set('auth', this.authString).set('Content-Type', 'application/json');
    const observable = this.http.delete<Grade>(`https://daycare.salmoncliff-9cdcd619.centralus.azurecontainerapps.io/grades/${id}`, {headers: headers})
    const grade = await firstValueFrom(observable);
  }
}
