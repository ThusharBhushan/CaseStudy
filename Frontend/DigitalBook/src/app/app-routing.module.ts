import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateBookComponent } from './createbook/create-book.component';

import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent },
  { path: 'createbook', component: CreateBookComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
