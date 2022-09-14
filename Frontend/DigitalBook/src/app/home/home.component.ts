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
  buyBookForReader: boolean = false;
  isPurchaseMessage: boolean = false;
  searchBookComponent: boolean = false;
  paymentId: String = '';
  bookList: any[] = [];
  book = {
    id: NaN,
    category: '',
    author: '',
    publisher: ''
  }
  reader = {
    userName: '',
    mailId: '',
    bookId: NaN
  }
  constructor(public bookService: BookserviceService, public route: Router) {
    // this.route.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
    this.isBookListAvailable = false;
    this.searchBookComponent = true;
    this.bookList = [];
  }

  searchBook() {
    const observable = this.bookService.searchBook(this.book);
    observable.subscribe((response: any) => {
      console.log(response);
      this.bookList = response;
      console.log(this.bookList);
      if (this.bookList != null) {
        this.isBookListAvailable = true;
        this.searchBookComponent =false;
      } else {
        alert("No Books Found");
      }
    },
      (error: any) => {
        console.log(error);
      }
    );
  }

  buyBook(selectedBook: any) {
    this.isBookListAvailable = false;
    this.buyBookForReader = true;
    console.log(selectedBook.id);
    this.reader.bookId = selectedBook.id;
   
  }

  onBuy() {
    const observable = this.bookService.buyBook(this.reader);
    observable.subscribe((response: any) => {
      console.log(response);
      this.paymentId = response.paymentid;
      this.isPurchaseMessage = true;
      this.buyBookForReader = false;
      this.isBookListAvailable = false;
      this.searchBookComponent=false;
    },
      (error: any) => {
        console.log(error);
      }
    );
  }

  searchMoreBook() {
    this.isBookListAvailable = false;
    location.reload();
    // this.route.navigate(["/home"],{skipLocationChange: true});
  }



}
