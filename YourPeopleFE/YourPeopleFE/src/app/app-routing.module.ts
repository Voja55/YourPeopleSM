import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomePageComponent} from "./components/home-page/home-page.component";
import {ProfileComponent} from "./components/acc/profile/profile.component";
import {LoginComponent} from "./components/acc/login/login.component";
import {RegisterComponent} from "./components/acc/register/register.component";

const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
