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
  paymentId : String ='';
  bookList: any[] = [];
  book = {
    id: NaN,
    category: '',
    author: '',
    publisher: ''
  }
  reader ={
    username :'',
    email :'',
    bookId:NaN
  }
  constructor(public bookService: BookserviceService, public route: Router) { }

  ngOnInit(): void {
    this.isBookListAvailable = false;
    this.bookList = [];
  }

  searchBook(book: any) {
    const observable = this.bookService.searchBook(book);
    observable.subscribe((response: any) => {
      console.log(response);
      this.bookList = response;
      console.log(this.bookList);
      if (this.bookList != null) {
        this.isBookListAvailable = true;
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
    this.buyBookForReader = true;
    console.log(selectedBook.id);
    this.reader.bookId=selectedBook.id;
  }

  onBuy(reader:any){
    const observable = this.bookService.buyBook(reader);
    observable.subscribe((response: any) => {
      console.log(response);
      this.paymentId = response.paymentid;
      this.buyBookForReader=false;
    },
      (error: any) => {
        console.log(error);
      }
    );
  }



}
