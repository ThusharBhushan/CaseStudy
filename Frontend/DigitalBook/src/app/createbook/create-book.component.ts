import { Component, OnInit } from '@angular/core';
import { BookserviceService } from '../bookservice.service';

@Component({
  selector: 'createbook',
  templateUrl: './create-book.component.html',
  styleUrls: ['./create-book.component.css']
})
export class CreateBookComponent implements OnInit {

  statusList: any = ['Active', 'Inactive'];  
  book = {
    title: '',
    category: '',
    logo :'',
    price: NaN,
    author: '',
    publisher: '',
    published_date: new Date(),
    active: '',
    userid: 1,
    content: ''
  }
  shortLink: string = "";
  selectedFile = null;
  constructor(public bookservice: BookserviceService) { }

  ngOnInit(): void {
  }

  createBook(book: any) {
    book.userid = localStorage.getItem('currentUserId');
    book.author = localStorage.getItem('currentUser');
    book.published_date = new Date();
    book.logo=this.shortLink;
    const observable = this.bookservice.createBook(book);
    observable.subscribe((responseBody: any) => {
      console.log(responseBody);
    },
      (error: any) => {
        console.log(error);
      }
    );
  }

  onFileUpload(event: any) {
    this.selectedFile = event.target.files[0];
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



}

