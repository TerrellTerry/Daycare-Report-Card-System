import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from  '@angular/common/http';
import { StudentTableComponent } from './components/student-table/student-table.component';
import { GradeTableComponent } from './components/grade-table/grade-table.component';
import { StudentPageComponent } from './components/student-page/student-page.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { GradePageComponent } from './components/grade-page/grade-page.component';
import { CreateStudentFormComponent } from './components/create-student-form/create-student-form.component';
import { CreateStudentPageComponent } from './components/create-student-page/create-student-page.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    StudentTableComponent,
    GradeTableComponent,
    StudentPageComponent,
    NavBarComponent,
    GradePageComponent,
    CreateStudentFormComponent,
    CreateStudentPageComponent,
    LoginFormComponent,
    LoginPageComponent,
    HomepageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
