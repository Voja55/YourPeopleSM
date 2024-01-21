import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {UserPayload} from "../../../model/payloads/user.payload";
import {UserService} from "../../../services/user.service";
import {throwError} from "rxjs";
import {PostService} from "../../../services/post.service";
import {GroupService} from "../../../services/group.service";
import {GroupPayload} from "../../../model/payloads/group.payload";
import {PostDisplayPayload} from "../../../model/payloads/postDisplay.payload";

@Component({
  selector: 'app-profile-visit',
  templateUrl: './profile-visit.component.html',
  styleUrls: ['./profile-visit.component.css']
})
export class ProfileVisitComponent implements OnInit{

  user: UserPayload;
  joinedGroups: GroupPayload[];
  posts: PostDisplayPayload[];
  groupsByUser: GroupPayload[];
  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private postService: PostService,
    private groupService: GroupService
  ) {}
  ngOnInit(): void {
    const username = this.route.snapshot.params['id'];
    this.userService.findByUsername(username).subscribe(data => {
      console.log(data);
      this.user = data;
    }, error => {
      throwError(error);
    });
    this.postService.getUsersPosts(username).subscribe(data => {
      console.log(data);
      this.posts = data;
    }, error => {
      throwError(error);
    });
    this.groupService.getUserGroups(username).subscribe(data => {
      console.log(data);
      this.groupsByUser = data;
    }, error => {
      throwError(error);
    });
    this.groupService.getUserMemberOf(username).subscribe(data => {
      console.log(data);
      this.joinedGroups = data;
    }, error => {
      throwError(error);
    });
  }

}
