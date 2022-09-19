import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookserviceService } from '../bookservice.service';


@Component({
  selector: 'createbook',
  templateUrl: './create-book.component.html',
  styleUrls: ['./create-book.component.css']
})
export class CreateBookComponent implements OnInit {

  statusList: any = ['ACTIVE', 'INACTIVE'];
  categoryList: any = ['HORROR', 'COMEDY', 'DRAMA'];
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
  shortLink: string = "";
  selectedFile = File;
  bookCreatedMessage: String = '';
  isBookCreated: boolean = false;
  errorMessage: string = '';
  isErrorMessage: boolean = false;
  fileUploadMessage: string = '';
  isFileUploadMessage: boolean = false;
  constructor(public bookservice: BookserviceService, public route: Router) {
    // this.route.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
  }

  createBook() {
    let userId: string | null = localStorage.getItem('currentUserId');
    let author: string | null = localStorage.getItem('currentUser');
    if (userId != null && author != null) {
      this.book.userid = parseInt(userId);
      this.book.author = author;
    }
    this.book.published_date = new Date();
    this.book.logo = this.shortLink;
    const observable = this.bookservice.createBook(this.book);
    observable.subscribe((responseBody: any) => {
      console.log(responseBody);
      if (responseBody.id != null) {
        this.bookCreatedMessage = 'Book Created Successfully!';
        this.isBookCreated = true;
        console.log(this.bookCreatedMessage);
      }

    },
      (error: any) => {
        console.log(error);
        this.isErrorMessage = true;
        this.errorMessage = error.error.text;
      }
    );
  }

  onFileUpload(event: any) {
    this.selectedFile = event.target.files[0];
    this.isFileUploadMessage = false;
    this.fileUploadMessage = "";
    if (!this.validateFile(this.selectedFile.name)) {
      console.log('Selected file format is not supported');
      this.isFileUploadMessage = true;
      this.fileUploadMessage = "Please select jpg/jpeg files";
    }
  }

  validateFile(name: any) {
    var ext = name.substring(name.lastIndexOf('.') + 1);
    if (ext.toLowerCase() == 'jpg' || ext.toLowerCase() == 'jpeg') {
      return true;
    }
    else {
      return false;
    }
  }

  onUpload() {
    console.log(this.selectedFile);
    this.bookservice.upload(this.selectedFile).subscribe(
      (event: any) => {
        if (typeof (event) === 'object') {
          this.shortLink = event.link;
        }
      }
    );
  }

  createNewBook() {
    this.isBookCreated = false;
    this.resetBook();
    this.route.navigate(["/createbook"]);
  }

  resetBook() {
    this.book.title = '',
      this.book.category = '',
      this.book.logo = '',
      this.book.price = NaN,
      this.book.author = '',
      this.book.publisher = '',
      this.book.published_date = new Date(),
      this.book.active = '',
      this.book.userid = 1,
      this.book.content = ''
  }



}

