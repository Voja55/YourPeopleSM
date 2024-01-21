import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomePageComponent} from "./components/home-page/home-page.component";
import {ProfileComponent} from "./components/acc/profile/profile.component";
import {LoginComponent} from "./components/acc/login/login.component";
import {RegisterComponent} from "./components/acc/register/register.component";
import {SearchFriendsComponent} from "./components/content/search/search-friends/search-friends.component";
import {ProfileVisitComponent} from "./components/content/profile-visit/profile-visit.component";
import {GroupComponent} from "./components/content/group/group.component";
import {GroupExploreComponent} from "./components/content/group-explore/group-explore.component";

const routes: Routes = [
  {path: 'home', component: HomePageComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'search-result', component: SearchFriendsComponent},
  {path: 'visit-profile/:id', component: ProfileVisitComponent},
  {path: 'group/:id', component: GroupComponent},
  {path: 'explore-groups', component: GroupExploreComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
