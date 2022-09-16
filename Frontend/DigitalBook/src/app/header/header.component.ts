import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';



@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  constructor(public route :Router,public authService :AuthService) {

  }

  ngOnInit(): void {
    this.isLoggedIn();
  }
  isLoggedIn(){
    console.log(localStorage.getItem('isLoggedIn'));
    let logInStatus :string|null = localStorage.getItem('isLoggedIn');
    if(logInStatus!=null){
      if(logInStatus.includes('true')){
           return true;
      }  
    }
    return false;
  }

  isLoggedInUserName(){
    return localStorage.getItem('currentUser');
  }
  signUp(){
    this.route.navigate(["/register"]);
  }
  logIn(){
    this.route.navigate(["/login"]);
  }
  onLogout(){ 
    this.authService.logout();   
  } 
  getAllBook(){
    this.route.navigate(["/allbook"]);
  }
  home(){
    localStorage.clear;
    this.route.navigate(['/home']);
    // window.location.reload();
  }
  

  
  
  
}
