import {Component, Input, OnInit} from '@angular/core';
import {Post} from "../../../model/post";
import {PostDisplayPayload} from "../../../model/payloads/postDisplay.payload";
import {CommentService} from "../../../services/comment.service";
import {CreateCommentPayload} from "../../../model/payloads/createComment.payload";
import {ReactionsService} from "../../../services/reactions.service";
import {CreateReactionPayload} from "../../../model/payloads/createReaction.payload";
import {throwError} from "rxjs";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit{
  @Input() post: PostDisplayPayload | undefined;
  commentToggle: boolean = false;
  //commentInput: string;
  createCommentPayload: CreateCommentPayload;
  reactPayload: CreateReactionPayload = {reaction: ''};
  commentForm: FormGroup;

  constructor(
    private commentService: CommentService,
    private reactService: ReactionsService
  ) {
    this.createCommentPayload = {
      text: ''
    }
  }

  ngOnInit() {
    this.commentForm = new FormGroup({
      commentInput: new FormControl('')
    })


  }

  reactLike(id: number | undefined) {
    this.reactPayload.reaction = 'LIKE';
    this.reactService.reactOnPost(this.reactPayload, id).subscribe(data =>{
      location.reload();
    }, error => {
      throwError(error);
    });
  }
  reactDislike(id: number | undefined) {
    this.reactPayload.reaction = 'DISLIKE';
    console.log(this.reactPayload.reaction);
    this.reactService.reactOnPost(this.reactPayload, id).subscribe(data =>{
      location.reload();
    }, error => {
      throwError(error);
    });
  }
  reactLove(id: number | undefined) {
    this.reactPayload.reaction = 'HEART';
    this.reactService.reactOnPost(this.reactPayload, id).subscribe(data =>{
      location.reload();
    }, error => {
      throwError(error);
    });
  }
  openComments(){
    this.commentToggle = !this.commentToggle;
  }

  commentOnPost() {
    this.createCommentPayload.text = this.commentForm.get('commentInput')?.value;
    console.log(this.createCommentPayload.text);
    this.commentService.createCom(this.createCommentPayload, this.post?.id).subscribe(data =>{
      location.reload();
    });
  }
}
