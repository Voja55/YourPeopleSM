import {Component, OnInit} from '@angular/core';
import {FriendPayload} from "../../../model/payloads/friend.payload";
import {FriendsService} from "../../../services/friends.service";

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css']
})
export class FriendsComponent implements OnInit{
  friends: FriendPayload[];

  constructor(
    private friendService: FriendsService
  ) {
  }

  ngOnInit() {
    this.friendService.getFriends().subscribe(data =>{
      this.friends = data;
    })
  }
}
