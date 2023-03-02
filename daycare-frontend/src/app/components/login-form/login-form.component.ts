import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginUtilServiceService } from 'src/app/services/login-util-service.service';
import { UserInfoService } from 'src/app/services/user-info.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  constructor(private loginService:LoginUtilServiceService, private router:Router, private userInfo:UserInfoService) { }

  ngOnInit(): void {
  }

  guardianUsername:string = '';
  guardianPassword:string = '';

  async validateLogin(){
    // const credentials:Credentials = {guardianUsername:this.guardianUsername,guardianPassword:this.guardianPassword}
    // const userToken:Token = await this.loginService.validateLogin(credentials)
    // if(userToken == null or if the status code is 404){tell them the login was invalid}
    // if(userToken.isTeacher){this.router.navigateByUrl("gradepage")}
    // if(userToken.isGuardian){this.router.navigateByUrl("studentpage")}
    // just for now, for testing
    const loginCreds = {username:this.guardianUsername, password:this.guardianPassword}
    const response = await this.loginService.login(JSON.stringify(loginCreds));
    if(response != null){
      localStorage.setItem("userInfo", JSON.stringify(response));
      this.router.navigateByUrl("/studentpage").then(() => {
        window.location.reload();
      });;;
      alert("Successfully logged in.");
    }
  }

}
