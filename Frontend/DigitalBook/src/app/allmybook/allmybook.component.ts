import { Component, OnInit } from '@angular/core';
import { BookserviceService } from '../bookservice.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-allmybook',
  templateUrl: './allmybook.component.html',
  styleUrls: ['./allmybook.component.css']
})
export class AllmybookComponent implements OnInit {
  book = {
    title: '',
    category: '',
    logo: '',
    price: NaN,
    author: '',
    publisher: '',
    published_date: new Date(),
    active: '',
    userid: 1,
    content: ''
  }
  bookList: any[] = [];
  readerPurchasedBooks = {
    bookId: NaN,
    paymentid: '',
    payment_date: null
  }
  isBookListAvailable: boolean = false;
  isReaderBookListAvailable: boolean = false;
  noBookFoundMessage: String = '';
  authorRole: boolean = false;
  readerRole: boolean = false;
  searchBookReaderPaymentId: String = '';
  dialogValue: String = "";

  constructor(public bookService: BookserviceService, public route: Router) { }

  ngOnInit(): void {
    console.log(localStorage.getItem('userRole'));
    let userRole: string | null = localStorage.getItem('userRole');
    if (userRole != null && userRole.includes('Author')) {
      this.getAllMyBooks();
      this.authorRole = true;
    } else if (userRole != null && userRole.includes('Reader')) {
      this.readerRole = true;
    }
  }
  validateWhiteSpace(): boolean {
    if (this.searchBookReaderPaymentId.trim() == '') {
      return true;
    }
    return false;
  }
  getAllMyBooks() {
    console.log(localStorage.getItem('currentUserId'));
    const observable = this.bookService.getBook(localStorage.getItem('currentUserId'));
    observable.subscribe((response: any) => {
      console.log(response);
      this.bookList = response;
      console.log(this.bookList);
      if (this.bookList != null) {
        this.isBookListAvailable = true;
      } else {
        this.noBookFoundMessage = "No Books Found";
      }
    },
      (error: any) => {
        console.log(error);
      }
    );
  }

  searchBookForReader() {
    const observable = this.bookService.searchBookForReader(this.searchBookReaderPaymentId);
    observable.subscribe((response: any) => {
      console.log(response);
      this.readerPurchasedBooks.paymentid = response.paymentid;
      this.readerPurchasedBooks.payment_date = response.payment_date;
      this.readerPurchasedBooks.bookId = response.bookid;
      console.log(this.readerPurchasedBooks);
      if (this.readerPurchasedBooks != null) {
        this.isReaderBookListAvailable = true;
        this.readerRole = false;
      } else {
        this.noBookFoundMessage = "No Books Found";
      }
    },
      (error: any) => {
        console.log(error);
      }
    );
  }

  viewBook(bookId: any, paymentId: any) {
    const observable = this.bookService.viewBook(bookId, paymentId);
    observable.subscribe((response: any) => {
      console.log(response);
    },
      (error: any) => {
        console.log(error);
      }
    );
  }

  editBook(book: any) {
    this.bookService.getEditBook(book);
    this.route.navigate(["/updatebook"]);
  }



}
