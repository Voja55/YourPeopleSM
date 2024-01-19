import {Component, OnInit} from '@angular/core';
import {group} from "@angular/animations";
import {UserPayload} from "../../../model/payloads/user.payload";
import {UserService} from "../../../services/user.service";
import {throwError} from "rxjs";
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{

  user!: UserPayload;
  groups: any;
  editToggle: boolean = false;
  editForm!: FormGroup;

  constructor(
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.userService.getProfile().subscribe(data => {
      this.user = data;
      console.log(this.user);
    }, error => {
      throwError(error);
    });
  }

  editProfileToggle() {
    if (!this.editToggle){
      this.editToggle = true;
    } else {
      this.editToggle = false;
    }
  }

  AcceptEdit() {

  }
}
