import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserFormComponent } from './user-form/user-form.component';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CreateBookComponent } from './createbook/create-book.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { AllmybookComponent } from './allmybook/allmybook.component';



@NgModule({
  declarations: [
    AppComponent,
    UserFormComponent,
    CreateBookComponent,
    LoginComponent,
    RegisterComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    AllmybookComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,HttpClientModule,FormsModule
  ],
  providers: [HeaderComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
