import {Component, OnInit} from '@angular/core';
import {GroupPayload} from "../../../model/payloads/group.payload";
import {throwError} from "rxjs";
import {GroupService} from "../../../services/group.service";

@Component({
  selector: 'app-group-explore',
  templateUrl: './group-explore.component.html',
  styleUrls: ['./group-explore.component.css']
})
export class GroupExploreComponent implements OnInit{
  groupsExplore: GroupPayload[];

  constructor(
    private groupService: GroupService
  ) {
  }

  ngOnInit() {
    this.groupService.getExploreGroups().subscribe(data => {
      console.log(data);
      this.groupsExplore = data;
    }, error => {
      throwError(error);
    })
  }

}
