import {Component, Input} from '@angular/core';
import {UserPayload} from "../../../model/payloads/user.payload";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../services/user.service";

@Component({
  selector: 'app-user-box',
  templateUrl: './user-box.component.html',
  styleUrls: ['./user-box.component.css']
})
export class UserBoxComponent {
  @Input() user: UserPayload | undefined;
  alreadyFriend: boolean = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
  ) {}

  gotoProfile(username: string | undefined) {
    this.router.navigate(['/visit-profile', username]);
  }

  sendRequest(username: string | undefined) {

  }
}
