import { Component, OnInit } from '@angular/core';
import { observable } from 'rxjs';
import { AuthService } from '../auth.service';

@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {


  form: any = {
    username: null,
    email: null,
    password: null
  };
  constructor(public authService :AuthService) { }

  ngOnInit(): void {
  }

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  onSubmit(): void {
    const { username, email, password } = this.form;
    const observable = this.authService.register(username, email, password);
    observable.subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

}
