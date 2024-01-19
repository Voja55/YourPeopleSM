import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../../services/authentification/auth.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{

  logged!: boolean;
  private loggedinSub!: Subscription;

  constructor(
    private authService: AuthService) {
  }

  ngOnInit(): void {
    this.logged = this.authService.isLoggedIn();
  }

  logout() {
    this.authService.logout();
  }




}
