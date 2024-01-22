import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {GroupService} from "../../services/group.service";
import {CreateGroupPayload} from "../../model/payloads/createGroup.payload";
import {Router} from "@angular/router";
import {throwError} from "rxjs";

@Component({
  selector: 'app-create-group',
  templateUrl: './create-group.component.html',
  styleUrls: ['./create-group.component.css']
})
export class CreateGroupComponent implements OnInit{
  createForm: FormGroup;
  createGroupPayload: CreateGroupPayload;

  constructor(
    private groupService: GroupService,
    private router: Router
  ) {
    this.createGroupPayload = {
      name: '',
      description: ''
    };
  }

  ngOnInit() {
    this.createForm = new FormGroup({
      name: new FormControl(''),
      description: new FormControl('')
    })
  }

  createGroup() {
    this.createGroupPayload.name = this.createForm.get('name')?.value;
    this.createGroupPayload.description = this.createForm.get('description')?.value;
    this.groupService.postCreateGroup(this.createGroupPayload).subscribe(data =>{
      this.router.navigateByUrl('/home');
    }, error => {
      throwError(error)
    })
  }
}
