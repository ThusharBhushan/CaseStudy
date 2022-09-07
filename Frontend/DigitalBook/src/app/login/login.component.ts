import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: any = {
    username: null,
    password: null
  };
  isLoginFailed = false;
  isLoggedIn = false;
  errorMessage = '';
  constructor(public authService: AuthService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const {username,password} =this.form;
    const observable=this.authService.login(username, password);
    observable.subscribe(
      data=>{
      console.log(data);
      this.isLoginFailed=false;
      this.isLoggedIn=true
      },
      err=> {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    

    );




  }

}
