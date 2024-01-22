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
import {AllGroupsComponent} from "./admin/all-groups/all-groups.component";
import {AllUsersComponent} from "./admin/all-users/all-users.component";
import {CreateGroupComponent} from "./components/create-group/create-group.component";
import {FriendsComponent} from "./components/content/friends/friends.component";

const routes: Routes = [
  {path: 'home', component: HomePageComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'search-result', component: SearchFriendsComponent},
  {path: 'visit-profile/:id', component: ProfileVisitComponent},
  {path: 'group/:id', component: GroupComponent},
  {path: 'explore-groups', component: GroupExploreComponent},
  {path: 'groups-admin', component: AllGroupsComponent},
  {path: 'users-admin', component: AllUsersComponent},
  {path: 'create-group', component: CreateGroupComponent},
  {path: 'friends', component: FriendsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
