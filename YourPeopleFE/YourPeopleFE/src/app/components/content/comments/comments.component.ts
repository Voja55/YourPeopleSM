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
  replies: Comment[];

  openReplies(){

    this.repliesToggle = !this.repliesToggle;
  }
}
