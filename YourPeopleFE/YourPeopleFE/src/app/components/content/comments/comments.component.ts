import {Component, Input, OnInit} from '@angular/core';
import {Post} from "../../../model/post";
import {Comment} from "../../../model/comment";
import {group} from "@angular/animations";
import {CommentService} from "../../../services/comment.service";

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit{
  @Input() post: number | undefined;
  repliesToggle: boolean = false;
  replytext: any;
  comments: any;
  replies: any;

  constructor(
    private commentService: CommentService
  ) {
  }

  ngOnInit(): void {
  }

  openReplies(){

    this.repliesToggle = !this.repliesToggle;
  }

  reply() {
    if (this.replytext.trim() ==''){
      console.log("input is empty")
    } else {
      //comment service
    }
  }


}
