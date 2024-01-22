import {Component, Input, OnInit} from '@angular/core';
import {GroupPayload} from "../../model/payloads/group.payload";
import {FormControl, FormGroup} from "@angular/forms";
import {GroupService} from "../../services/group.service";

@Component({
  selector: 'app-group-box',
  templateUrl: './group-box.component.html',
  styleUrls: ['./group-box.component.css']
})
export class GroupBoxComponent implements OnInit{
  @Input() group: GroupPayload;
  suspendToggleBool: boolean = false;
  suspendForm: FormGroup;

  constructor(
    private groupService: GroupService
  ) {
  }
  ngOnInit() {
    this.suspendForm = new FormGroup( {
      reason: new FormControl('')
    })
  }
  suspend(id: number) {
    this.groupService.suspendGroup(this.group.id, this.suspendForm.get('reason')?.value).subscribe(data =>{
      this.suspendToggle();
    })
    console.log(id);
  }
  suspendToggle() {
    this.suspendToggleBool = !this.suspendToggleBool;
  }
}
