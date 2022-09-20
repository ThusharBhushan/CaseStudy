import { Component, OnInit } from '@angular/core';
import { observable } from 'rxjs';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  roleList =['Author','Reader'];
  form: any = {
    username: '',
    email: '',
    password: '',
    userrole :''
  };
  constructor(public authService :AuthService,public route: Router) { }

  ngOnInit(): void {
  }

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  
  validateWhiteSpace():boolean{
    if(this.form.username.trim() == '')
    {
      return true ;
    }
    return false;
  }
  onSubmit(): void {
    const { username, email, password,userrole } = this.form;
    const observable = this.authService.register(username, email, password,userrole);
    observable.subscribe(
      (data:any)=> {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        // this.route.navigate(['/login']);
      },
      (err :HttpErrorResponse) => {
        this.errorMessage = err.error.text;
        console.log(this.errorMessage);
        alert(this.errorMessage);
        this.isSuccessful = false;
        this.isSignUpFailed = true;
      }
    );
  }

}
