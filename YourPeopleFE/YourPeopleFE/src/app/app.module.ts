import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/acc/login/login.component';
import { RegisterComponent } from './components/acc/register/register.component';
import { ProfileComponent } from './components/acc/profile/profile.component';
import { HeaderComponent } from './components/header/header.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { PostsComponent } from './components/content/posts/posts.component';
import { CommentsComponent } from './components/content/comments/comments.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    HeaderComponent,
    HomePageComponent,
    PostsComponent,
    CommentsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
