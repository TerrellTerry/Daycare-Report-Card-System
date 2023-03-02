import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JwtDecoderService } from 'src/app/services/jwt-decoder.service';
import { LoginUtilServiceService } from 'src/app/services/login-util-service.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(private jwtDecoder:JwtDecoderService, private router:Router) { }

  role:string = this.jwtDecoder.parseJwt().role;
  loggedIn:boolean = false;

  ngOnInit(): void {
    if(this.role != null){
      this.loggedIn = true;
    }
  }
  
  logoutUser(){
    localStorage.clear();
    alert("Successfully logged out. Redirecting to homepage.")
    this.router.navigateByUrl("/home").then(() => {
      window.location.reload();
    });;
  }
}
