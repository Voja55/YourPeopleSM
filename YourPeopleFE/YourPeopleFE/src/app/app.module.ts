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
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {TokenInterceptorService} from "./services/authentification/token-interceptor.service";
import { SearchFriendsComponent } from './components/content/search/search-friends/search-friends.component';
import { UserBoxComponent } from './components/content/user-box/user-box.component';
import { ProfileVisitComponent } from './components/content/profile-visit/profile-visit.component';
import { UserBarComponent } from './components/content/user-bar/user-bar.component';
import { GroupComponent } from './components/content/group/group.component';
import { GroupExploreComponent } from './components/content/group-explore/group-explore.component';
import { AllUsersComponent } from './admin/all-users/all-users.component';
import { AllGroupsComponent } from './admin/all-groups/all-groups.component';
import { GroupBoxComponent } from './admin/group-box/group-box.component';
import { CreateGroupComponent } from './components/create-group/create-group.component';
import { FriendsComponent } from './components/content/friends/friends.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    HeaderComponent,
    HomePageComponent,
    PostsComponent,
    CommentsComponent,
    SearchFriendsComponent,
    UserBoxComponent,
    ProfileVisitComponent,
    UserBarComponent,
    GroupComponent,
    GroupExploreComponent,
    AllUsersComponent,
    AllGroupsComponent,
    GroupBoxComponent,
    CreateGroupComponent,
    FriendsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
  //  { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
