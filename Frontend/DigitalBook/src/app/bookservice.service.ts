import { HttpClient, HttpClientModule, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';


// const URL = "http://localhost:8083/api/v1/digitalbooks/";
const URL = "https://bn6p12jexi.execute-api.us-west-2.amazonaws.com/prod/";

@Injectable({
  providedIn: 'root'
})

export class BookserviceService {
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
  updateBook ={
    authorId:NaN,
    bookId:NaN
  }
  baseApiUrl = "https://file.io";
  constructor(public http: HttpClient) { }
  createBook(book: any) {
    // https://bn6p12jexi.execute-api.us-west-2.amazonaws.com/prod/{proxy+}author/{authorId}/books
    let appendedURL = URL + "author/" + book.userid + "/books";
    return this.http.post(appendedURL, book);

  }

  

  searchBook(book: any) {
    let appendedURL = URL + "author/books/search"
    let queryParams = new HttpParams();
    queryParams = queryParams.append("category", book.category);
    queryParams = queryParams.append("author", book.author);
    queryParams = queryParams.append("publisher", book.publisher)
    return this.http.get(appendedURL, { params: queryParams });

  }

  getBook(userid: any) {
    let appendedURL = URL + "/allbooks/" + userid;
    return this.http.get(appendedURL);
  }

  getEditBook(book: any) {
    return this.book = book;
  }

  upload(file: any) {
    const formData = new FormData();
    formData.append("file", file, file.name);
    return this.http.post(this.baseApiUrl, formData)
  }

  buyBook(reader: any) {
    // payment/book/{bookId}/username/{username}/mailId/{mailId}
    let appendedURL = URL + "/payment/book/" + reader.bookId + "/username/" + reader.userName + "/mailId/" + reader.mailId;
    return this.http.post(appendedURL, reader);

  }

  searchBookForReader(searchBookReaderPaymentId: String) {
    // "/reader/payment/{paymentId}"
    let appendedURL = URL + "/reader/payment/" + searchBookReaderPaymentId;
    return this.http.get(appendedURL);
  }

  viewBook(bookId: any, paymentId: any) {
    // "reader/{bookId}/payment/{paymentId}
    let appendedURL = URL + "/reader/" + bookId + "/payment/" + paymentId;
    return this.http.get(appendedURL);
  }

  editBook(authorId: any, bookId: any,book:any) {
    this.updateBook.authorId=authorId;
    this.updateBook.bookId=bookId;
    // author/{authorId}/books/{bookId}
    let appendedURL = URL + "author/" + authorId + "/books/" + bookId;
    return this.http.put(appendedURL, book);

  }
}
