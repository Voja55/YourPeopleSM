import {Component, OnInit} from '@angular/core';
import {group} from "@angular/animations";
import {UserPayload} from "../../../model/payloads/user.payload";
import {UserService} from "../../../services/user.service";
import {throwError} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UpdateInfoPayload} from "../../../model/payloads/updateInfo.payload";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {PassChangePayload} from "../../../model/payloads/passChange.payload";
import {Post} from "../../../model/post";
import {PostService} from "../../../services/post.service";
import {PostDisplayPayload} from "../../../model/payloads/postDisplay.payload";
import {GroupPayload} from "../../../model/payloads/group.payload";
import {GroupService} from "../../../services/group.service";
import {RequestPayload} from "../../../model/payloads/request.payload";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{

  user!: UserPayload;
  editToggle: boolean = false;
  passToggle: boolean = false;
  editForm: FormGroup;
  updateInfoPayload: UpdateInfoPayload;
  passChangePayload: PassChangePayload;
  passForm: FormGroup;
  posts: PostDisplayPayload[];
  groupsByYou: GroupPayload[];
  yourGroups: GroupPayload[];
  waitinReqs: RequestPayload[];

  constructor(
    private userService: UserService,
    private postService: PostService,
    private groupService: GroupService
  ) {
    this.updateInfoPayload = {
      description: '',
      email: '',
      name: '',
      surname: '',
    }
    this.passChangePayload = {
      oldPass: '',
      newPass1: '',
      newPass2: ''
    }
  }

  ngOnInit(): void {
    this.userService.getProfile().subscribe(data => {
      this.user = data;
      console.log(this.user);

      this.editForm = new FormGroup({
        nameEdit: new FormControl(this.user.name, Validators.required),
        surnameEdit: new FormControl(this.user.surname, Validators.required),
        emailEdit: new FormControl(this.user.email, Validators.required),
        descEdit: new FormControl(this.user.description)
      })

      this.postService.getMyPosts().subscribe(data => {
        console.log(data);
        this.posts = data;
      }, error => {
        throwError(error);
      });

      this.groupService.getMyGroups().subscribe(data => {
        console.log(data);
        this.groupsByYou = data;
      }, error => {
        throwError(error);
      })

      this.groupService.getJoinedGroups().subscribe(data => {
        console.log(data);
        this.yourGroups = data;
      }, error => {
        throwError(error);
      })

      this.groupService.waitingGroupReqs().subscribe(data => {
        console.log(data);
        this.waitinReqs = data;
      })

    }, error => {
      throwError(error);
    });

    this.passForm = new FormGroup({
      oldPass: new FormControl('', Validators.required),
      newPass1: new FormControl('', Validators.required),
      newPass2: new FormControl('', Validators.required),
    })

  }

  editProfileToggle() {
    if (!this.editToggle){
      this.editToggle = true;
    } else {
      this.editToggle = false;
    }
  }
  passChangeToggle() {
    if (!this.passToggle){
      this.passToggle = true;
    } else {
      this.passToggle = false;
    }
  }

  AcceptEdit() {
    this.updateInfoPayload.name = this.editForm.get('nameEdit')?.value;
    this.updateInfoPayload.surname = this.editForm.get('surnameEdit')?.value;
    this.updateInfoPayload.email = this.editForm.get('emailEdit')?.value;
    this.updateInfoPayload.description = this.editForm.get('descEdit')?.value;

    this.userService.updateProfile(this.updateInfoPayload).subscribe(data => {
      this.user = data;
    }, error => {
      throwError(error);
    });
    this.editToggle = false;
  }

  ChangePass(){
    this.passChangePayload.oldPass = this.passForm.get('oldPass')?.value;
    this.passChangePayload.newPass1 = this.passForm.get('newPass1')?.value;
    this.passChangePayload.newPass2 = this.passForm.get('newPass2')?.value;

    this.userService.changePass(this.passChangePayload).subscribe(data => {
      this.passToggle = false;
    }, error => {
      throwError(error);
    });

  }


  acceptGReq(id: number) {
    this.groupService.acceptGroupReq(id).subscribe(data => {
      window.location.reload();
    })
  }

  declineGReq(id: number) {
    this.groupService.declineGroupReq(id).subscribe(data => {
      window.location.reload();
    })
  }
}
