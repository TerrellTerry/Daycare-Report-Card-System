import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateStudentPageComponent } from './components/create-student-page/create-student-page.component';
import { GradePageComponent } from './components/grade-page/grade-page.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { StudentPageComponent } from './components/student-page/student-page.component';

const routes: Routes = [
  {path:"", component:HomepageComponent},
  {path:"home", component:HomepageComponent},
  {path:"login", component:LoginPageComponent},
  {path:"studentpage", component:StudentPageComponent},
  {path:"gradepage", component:GradePageComponent},
  {path:"createstudentpage", component:CreateStudentPageComponent},
  {path:'**', component:HomepageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
