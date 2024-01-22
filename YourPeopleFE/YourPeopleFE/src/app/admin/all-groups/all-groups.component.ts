import {Component, OnInit} from '@angular/core';
import {GroupPayload} from "../../model/payloads/group.payload";
import {GroupService} from "../../services/group.service";
import {throwError} from "rxjs";
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-all-groups',
  templateUrl: './all-groups.component.html',
  styleUrls: ['./all-groups.component.css']
})
export class AllGroupsComponent implements OnInit{
  groups: GroupPayload[];

  constructor(
    private groupService: GroupService
  ) {
  }

  ngOnInit() {
    this.groupService.getAdminGroups().subscribe(data => {
      console.log(data);
      this.groups = data;
    }, error => {
      throwError(error);
    })
  }
}
