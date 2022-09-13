import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../app/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  constructor(public authService :AuthService,public route:Router) { }
  ngOnInit(): void {
        this.route.navigate(["/home"]);
  }
  title = 'DigitalBook';
}
