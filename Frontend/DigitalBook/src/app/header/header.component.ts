import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';



@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  constructor(public route :Router,public authService :AuthService) {}

  ngOnInit(): void {
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('currentUser');
  }
  isLoggedIn(){
    return localStorage.getItem('isLoggedIn');
  }

  isLoggedInUserName(){
    return localStorage.getItem('currentUser');
  }

  onLogout(){ 
    this.authService.logout();   
  } 
  

  
  
  
}
