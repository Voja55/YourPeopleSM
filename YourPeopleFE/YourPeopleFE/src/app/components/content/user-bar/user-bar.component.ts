import {Component, Input} from '@angular/core';
import {UserPayload} from "../../../model/payloads/user.payload";
import {ActivatedRoute, Router} from "@angular/router";
import {FriendsService} from "../../../services/friends.service";
import {FriendPayload} from "../../../model/payloads/friend.payload";

@Component({
  selector: 'app-user-bar',
  templateUrl: './user-bar.component.html',
  styleUrls: ['./user-bar.component.css']
})
export class UserBarComponent {
  @Input() user: FriendPayload | undefined;
  alreadyFriend: boolean = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private friendsService: FriendsService
  ) {}

  gotoProfile(username: string | undefined) {
    this.router.navigate(['/visit-profile', username]);
  }

  sendRequest(username: string | undefined) {
    this.friendsService.postSendFriendReq(username).subscribe(data => {
      window.location.reload();
    })
  }
}
