import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../../services/user.service";
import {UserPayload} from "../../../../model/payloads/user.payload";
import {throwError} from "rxjs";

@Component({
  selector: 'app-search-friends',
  templateUrl: './search-friends.component.html',
  styleUrls: ['./search-friends.component.css']
})
export class SearchFriendsComponent implements OnInit{

  users: UserPayload[];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService
  ) {}
  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      const searchInput = params['query'];
      console.log('Params:' + searchInput);
      this.userService.searchUsers(searchInput).subscribe(data => {
        console.log(data);
        this.users = data;
      }, error => {
        throwError(error);
      });
    });
  }

  gotoProfile(username: string) {
    this.router.navigate(['/visit-profile', username]);
  }
}
