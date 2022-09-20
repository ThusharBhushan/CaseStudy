import { Component, OnInit } from '@angular/core';
import { AllmybookComponent } from '../allmybook/allmybook.component';
import { BookserviceService } from '../bookservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-updatebook',
  templateUrl: './updatebook.component.html',
  styleUrls: ['./updatebook.component.css']
})
export class UpdatebookComponent implements OnInit {
  book = {
    id: NaN,
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
  statusList: any = ['ACTIVE', 'INACTIVE'];
  categoryList: any = ['HORROR', 'COMEDY', 'DRAMA'];
  shortLink: string = "";
  selectedFile = File;
  fileUploadMessage: string = '';
  isFileUploadMessage: boolean = false;
  isBookCreated: boolean = false;
  errorMessage: string = '';
  isErrorMessage: boolean = false;
  bookCreatedMessage: String = '';
  constructor(public bookService: BookserviceService, public route: Router) { }

  ngOnInit(): void {
    this.book = this.bookService.book;
  }
  updateBook(bookId: any) {
    let authorId: string | null = localStorage.getItem('currentUserId');
    const observable = this.bookService.editBook(authorId, bookId,this.book);
    observable.subscribe((response: any) => {
      console.log(response);
      this.bookCreatedMessage = 'Book Updated Successfully!';
      this.isBookCreated = true;
    },
      (error: any) => {
        console.log(error);
        this.errorMessage=error.error.text;
      }
    );
  }

  onClose(){
    this.bookService.resetBook();
    this.route.navigate(["/allbook"]);
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
    this.bookService.upload(this.selectedFile).subscribe(
      (event: any) => {
        if (typeof (event) === 'object') {
          this.shortLink = event.link;
        }
      }
    );
  }

  createNewBook() {
    this.isBookCreated = false;
    this.bookService.resetBook();
    this.route.navigate(["/allbook"]);
  }
}
