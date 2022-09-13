import { Component, OnInit } from '@angular/core';
import { BookserviceService } from '../bookservice.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  isBookListAvailable: boolean = false;
  bookList: any[] = [];
  book = {
    id: NaN,
    category: '',
    author: '',
    publisher: ''
  }
  constructor(public bookService: BookserviceService, public route: Router) { }

  ngOnInit(): void {
    this.isBookListAvailable = false;
    this.bookList=[];
  }

  searchBook(book: any) {
    const observable = this.bookService.searchBook(book);
    observable.subscribe((response: any) => {
      console.log(response);
      this.bookList = response;
      console.log(this.bookList);
      if (this.bookList != null) {
        this.isBookListAvailable = true;
      }else{
        alert("No Books Found");
      }
    },
      (error: any) => {
        console.log(error);
      }
    );
  }


}
