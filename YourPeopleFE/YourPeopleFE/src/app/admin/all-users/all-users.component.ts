import {Component, OnInit} from '@angular/core';
import {UserPayload} from "../../model/payloads/user.payload";
import {UserService} from "../../services/user.service";
import {throwError} from "rxjs";

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.css']
})
export class AllUsersComponent implements OnInit{
  users: UserPayload[];

  constructor(
    private userService: UserService
  ) {
  }

  ngOnInit(): void {
    this.userService.getAllAdmin().subscribe(data =>{
      console.log(data);
      this.users = data;
    }, error => {
      throwError(error);
    })
  }

}
