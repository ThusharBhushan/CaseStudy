import { Component, OnInit } from '@angular/core';
import { BookserviceService } from '../bookservice.service';

@Component({
  selector: 'app-allmybook',
  templateUrl: './allmybook.component.html',
  styleUrls: ['./allmybook.component.css']
})
export class AllmybookComponent implements OnInit {
  bookList: any[] = [];
  readerPurchasedBooks: any[] = [];
  isBookListAvailable: boolean = false;
  isReaderBookListAvailable: boolean = false;
  noBookFoundMessage: String = '';
  authorRole: boolean = false;
  readerRole: boolean = false;
  searchBookReaderPaymentId: String = '';

  constructor(public bookService: BookserviceService) { }

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

  searchBookForReader(searchBookReaderPaymentId: String) {
    const observable = this.bookService.searchBookForReader(searchBookReaderPaymentId);
    observable.subscribe((response: any) => {
      console.log(response);
      this.readerPurchasedBooks = response;
      console.log(this.readerPurchasedBooks);
      if (this.readerPurchasedBooks != null) {
        this.isReaderBookListAvailable = true;
        this.readerRole=false;
      } else {
        this.noBookFoundMessage = "No Books Found";
      }
    },
      (error: any) => {
        console.log(error);
      }
    );
  }


}
