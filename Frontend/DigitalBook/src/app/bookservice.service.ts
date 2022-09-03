import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';


const URL = "http://localhost:8083/api/v1/digitalbooks";
@Injectable({
  providedIn: 'root'
})

export class BookserviceService {

 

  constructor(public http :HttpClient) { }

  createBook(book :any){
    let appendedURL = URL +"/author/" + book.userid +"/books"
    return this.http.post(appendedURL,book);

  }
}
