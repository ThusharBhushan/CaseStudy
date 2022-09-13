import { HttpClient, HttpClientModule, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';


const URL = "http://localhost:8083/api/v1/digitalbooks";
@Injectable({
  providedIn: 'root'
})

export class BookserviceService {
  baseApiUrl = "https://file.io";
  constructor(public http: HttpClient) { }
  createBook(book: any) {
    let appendedURL = URL + "/author/" + book.userid + "/books"
    return this.http.post(appendedURL, book);

  }

  searchBook(book: any) {
    let appendedURL = URL + "/books/search"
    let queryParams = new HttpParams();
    queryParams = queryParams.append("category", book.category);
    queryParams = queryParams.append("author", book.author);
    queryParams = queryParams.append("publisher", book.publisher)
    return this.http.get(appendedURL, { params: queryParams });

  }

  upload(file:any) {
    const formData = new FormData();
    formData.append("file", file, file.name);
    return this.http.post(this.baseApiUrl, formData)
  }
}
