import { Component, OnInit } from '@angular/core';
import { AuthService } from '../app/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  isLoggedIn = false;
  constructor(public authService :AuthService) { }
  ngOnInit(): void {

    if (this.isLoggedIn) {
    }
  }
  title = 'DigitalBook';
}
