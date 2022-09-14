import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  constructor(public authService: AuthService, public route: Router) { }

  ngOnInit(): void {
  }



  onSubmit(): void {
    const { username, password } = this.form;
    const promise = this.authService.login(username, password);
    promise.subscribe(
      (responseBody: any) => {
        console.log(responseBody);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        localStorage.setItem('currentUser', responseBody.username);
        localStorage.setItem('currentUserId', responseBody.id);
        localStorage.setItem('isLoggedIn', 'true');
        let userRole: string = responseBody.userrole;
        localStorage.setItem('userRole', userRole);
        if (userRole.includes('Author')) {
          this.route.navigate(["/createbook"]);
        } else {
          this.route.navigate(["/allbook"]);
        }
      },
      (err: any) => {
        this.errorMessage = err.error.message;
        console.log('Error :' + this.errorMessage);
        this.isLoginFailed = true;
        localStorage.setItem('isLoggedIn', 'false');
        localStorage.removeItem('currentUser');
        localStorage.removeItem('currentUserId');
      }
    );

  }
}
