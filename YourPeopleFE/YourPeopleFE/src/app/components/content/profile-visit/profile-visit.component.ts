import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {UserPayload} from "../../../model/payloads/user.payload";
import {UserService} from "../../../services/user.service";
import {throwError} from "rxjs";

@Component({
  selector: 'app-profile-visit',
  templateUrl: './profile-visit.component.html',
  styleUrls: ['./profile-visit.component.css']
})
export class ProfileVisitComponent implements OnInit{

  user: UserPayload;
  constructor(
    private route: ActivatedRoute,
    private userService: UserService
  ) {}
  ngOnInit(): void {
    const username = this.route.snapshot.params['id'];
    this.userService.findByUsername(username).subscribe(data => {
      console.log(data);
      this.user = data;
    }, error => {
      throwError(error);
    })
  }

}
