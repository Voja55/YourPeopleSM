import {Component, Input, OnInit} from '@angular/core';
import {Post} from "../../../model/post";
import {Comment} from "../../../model/comment";
import {group} from "@angular/animations";
import {CommentService} from "../../../services/comment.service";
import {CommentPayload} from "../../../model/payloads/comment.payload";
import {FormControl, FormGroup} from "@angular/forms";
import {CreateCommentPayload} from "../../../model/payloads/createComment.payload";
import {throwError} from "rxjs";
import {CreateReactionPayload} from "../../../model/payloads/createReaction.payload";
import {ReactionsService} from "../../../services/reactions.service";

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit{
  @Input() post: number | undefined;
  @Input() comment: number | undefined;
  repliesToggle: boolean = false;
  createReplyPayload: CreateCommentPayload;
  comments: CommentPayload[];
  reactPayload: CreateReactionPayload = {reaction: ''};
  replyForm: FormGroup;

  constructor(
    private commentService: CommentService,
    private reactService: ReactionsService
  ) {
    this.createReplyPayload = {
      text: ''
    }
  }

  ngOnInit(): void {
    if(this.post == undefined){
      this.commentService.getReplies(this.comment).subscribe(data =>{
        this.comments = data;
      })
    }
    if(this.comment == undefined){
      this.commentService.getComments(this.post).subscribe(data =>{
        this.comments = data;
      })
    }
    this.replyForm = new FormGroup({
      commentInput: new FormControl('')
    })

  }
  openReplies(){
    this.repliesToggle = !this.repliesToggle;
  }

  reactLike(id: number) {
    this.reactPayload.reaction = 'LIKE';
    console.log(this.reactPayload.reaction);
    this.reactService.reactOnComment(this.reactPayload, id).subscribe(data =>{
      location.reload();
    }, error => {
      throwError(error);
    });
  }

  reactDislike(id: number) {
    this.reactPayload.reaction = 'DISLIKE';
    console.log(this.reactPayload.reaction);
    this.reactService.reactOnComment(this.reactPayload, id).subscribe(data =>{
      location.reload();
    }, error => {
      throwError(error);
    });
  }

  reactLove(id: number) {
    this.reactPayload.reaction = 'HEART';
    console.log(this.reactPayload.reaction);
    this.reactService.reactOnComment(this.reactPayload, id).subscribe(data =>{
      location.reload();
    }, error => {
      throwError(error);
    });
  }

  replyToComment(id: number) {
    this.createReplyPayload.text = this.replyForm.get('commentInput')?.value;
    console.log(this.createReplyPayload.text);
    this.commentService.createReplie(this.createReplyPayload, id).subscribe(data =>{
      location.reload();
    });
  }
}
