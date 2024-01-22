import {Component, Input, OnInit} from '@angular/core';
import {UserPayload} from "../../../model/payloads/user.payload";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../services/user.service";
import {FriendsService} from "../../../services/friends.service";

@Component({
  selector: 'app-user-box',
  templateUrl: './user-box.component.html',
  styleUrls: ['./user-box.component.css']
})
export class UserBoxComponent implements OnInit{
  @Input() user: UserPayload | undefined;
  alreadyFriend: boolean = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private friendsService: FriendsService
  ) {}

  ngOnInit() {
    //this.friendsService.
  }

  gotoProfile(username: string | undefined) {
    this.router.navigate(['/visit-profile', username]);
  }

  sendRequest(username: string | undefined) {
    this.friendsService.postSendFriendReq(username).subscribe(data => {
      window.location.reload();
    })
  }
}
