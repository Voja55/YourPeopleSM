import {Component, OnInit} from '@angular/core';
import {GroupService} from "../../../services/group.service";
import {ActivatedRoute} from "@angular/router";
import {GroupPayload} from "../../../model/payloads/group.payload";
import {PostDisplayPayload} from "../../../model/payloads/postDisplay.payload";
import {FormControl, FormGroup} from "@angular/forms";
import {CreatePostPayload} from "../../../model/payloads/createPost.payload";
import {PostService} from "../../../services/post.service";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {throwError} from "rxjs";

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit{
  group: GroupPayload;
  accessToggle: boolean = false;
  postCreationToggle: boolean = false;
  createpostForm: FormGroup;
  createpostPayload: CreatePostPayload;
  posts:  PostDisplayPayload[];

  constructor(
    private route: ActivatedRoute,
    private groupService: GroupService,
    private postService: PostService
  ) {
    this.createpostPayload = {
    content: ''
    };
  }

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    this.groupService.getGroup(id).subscribe(data => {
      console.log(data);
      this.group = data;
      if(data.joined){
        this.accessToggle = true;
        this.postService.getGroupPosts(id).subscribe(data =>{
          console.log(data);
          this.posts = data;
        }, error =>{
          throwError(error);
        });
      }
    })
    console.log(this.posts)
    this.createpostForm = new FormGroup({
      content: new FormControl('')
    });
  }
  createPostToggle() {
    this.postCreationToggle = !this.postCreationToggle;
  }

  createPost(){
    this.createpostPayload.content = this.createpostForm.get('content')?.value;
    console.log(this.createpostPayload);

    this.postService.postCreatePost(this.group.id, this.createpostPayload).subscribe(data =>{
      this.createPostToggle();
      window.location.reload();
    }, error1 => {
      throwError(error1);
    })
  }

  sendReq() {

  }


}
