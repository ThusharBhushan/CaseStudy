import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllmybookComponent } from './allmybook/allmybook.component';
import { CreateBookComponent } from './createbook/create-book.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';

import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UpdatebookComponent } from './updatebook/updatebook.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: "allbook", component: AllmybookComponent},
  { path: "header", component: HeaderComponent},
  { path: "updatebook", component: UpdatebookComponent},
  { path: "createbook", component: CreateBookComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
