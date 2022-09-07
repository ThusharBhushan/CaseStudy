import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
const AUTH_API = '';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(public http: HttpClient) { }
  login(username: string, password: string) {
    return this.http.post(AUTH_API + 'signin', {
      username,
      password
    });
  }

  register(username: string, email: string, password: string) {
    return this.http.post(AUTH_API + 'signup', {
      username,
      email,
      password
    });
  }
}
