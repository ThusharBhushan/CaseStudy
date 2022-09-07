import { Component, OnInit } from '@angular/core';
import { BookserviceService } from '../bookservice.service';

@Component({
  selector: 'createbook',
  templateUrl: './create-book.component.html',
  styleUrls: ['./create-book.component.css']
})
export class CreateBookComponent implements OnInit {
  

  book = {
    title: '',
    category: '',
    price: '',
    author: '',
    publisher :'',
    published_date :'',
    active:'',
    userid: 1,
    content:''
  }
  constructor(public bookservice :BookserviceService) { }

  ngOnInit(): void {
  }

  createBook(){
    this.book.userid=1;
    this.book.author='Mary'
    this.book.published_date='03-09-2020';
    const observable = this.bookservice.createBook(this.book);
    observable.subscribe((responseBody: any) => {
      console.log(responseBody);
    },
      (error: any) => {
        console.log(error);
      }
    );
  }
  
   

  }

