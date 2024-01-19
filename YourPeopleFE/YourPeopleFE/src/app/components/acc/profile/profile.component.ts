import {Component, OnInit} from '@angular/core';
import {group} from "@angular/animations";
import {UserPayload} from "../../../model/payloads/user.payload";
import {UserService} from "../../../services/user.service";
import {throwError} from "rxjs";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{

  user!: UserPayload;
  groups: any;

  constructor(
    private userService: UserService
  ) {
    // this.userService.getProfile().subscribe(data => {
    //   this.user = data;
    //   console.log(this.user);
    // }, error => {
    //   throwError(error);
    // });
  }

  ngOnInit(): void {
    this.userService.getProfile().subscribe(data => {
      this.user = data;
      console.log(this.user);
    }, error => {
      throwError(error);
    });
  }

  getProfile(){

  }


  editProfile() {

  }




}
