import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../../services/authentification/auth.service";
import {Subscription} from "rxjs";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{

  logged: boolean;
  user: boolean;
  admin: boolean;

  private loggedinSub!: Subscription;
  searchInput: string;

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private router: Router) {
  }

  ngOnInit(): void {
    this.logged = this.authService.isLoggedIn();
    if(this.logged){
      this.admin = this.authService.hasRole('ROLE_ADMIN');
      this.user = this.authService.hasRole('ROLE_USER');
    }

  }

  logout() {
    this.authService.logout();
  }


  search(searchInput: string) {
    this.router.navigate(['/search-result'], {queryParams: {query: searchInput}})
  }
}
