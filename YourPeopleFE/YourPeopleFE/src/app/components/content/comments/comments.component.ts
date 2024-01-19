import {Component, Input} from '@angular/core';
import {Post} from "../../../model/post";
import {Comment} from "../../../model/comment";
import {group} from "@angular/animations";

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent {
  @Input() post: Post | undefined;
  repliesToggle: boolean = false;
  replytext: any;
  comments: any;
  replies: any;

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
