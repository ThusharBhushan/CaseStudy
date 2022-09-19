import { Component, OnInit } from '@angular/core';
import { BookserviceService } from '../bookservice.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-allmybook',
  templateUrl: './allmybook.component.html',
  styleUrls: ['./allmybook.component.css']
})
export class AllmybookComponent implements OnInit {
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
  dialogValue:String="";

  constructor(public bookService: BookserviceService,public dialog: MatDialog) { }

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
  // openDialog() {
  //     const dialogRef = this.dialog.open(UpdateBookComponent, {
  //       width: '250px',
  //       backdropClass: 'custom-dialog-backdrop-class',
  //       panelClass: 'custom-dialog-panel-class',
  //       disableClose:true,
  //       data: { pageValue: this.bookList }
  //     });
  
  //     dialogRef.afterClosed().subscribe(result => {
  //       console.log('The dialog was closed', result);
  //       this.dialogValue = result.data;
  //     });
  // }
  updateBook(authorId: any, bookId: any) {
    const observable = this.bookService.editBook(authorId, bookId);
    observable.subscribe((response: any) => {
      console.log(response);
    },
      (error: any) => {
        console.log(error);
      }
    );
  }


}
