import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../app/auth.service';
import { HeaderComponent } from './header/header.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  constructor(public route:Router) { }
  ngOnInit(): void {
        localStorage.clear();
        this.route.navigate(["/home"]);
  }
  title = 'DigitalBook';
}
