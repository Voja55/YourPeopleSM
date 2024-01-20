import {Component, Input} from '@angular/core';
import {Post} from "../../../model/post";
import {PostDisplayPayload} from "../../../model/payloads/postDisplay.payload";
import {CommentService} from "../../../services/comment.service";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent {
  @Input() post: PostDisplayPayload | undefined;
  commentToggle: boolean = false;
  commentInput: string;

  constructor(
    private commentService: CommentService
  ) {}

  reactLike() {

  }
  reactDislike() {

  }
  reactLove() {

  }
  openComments(){
    this.commentToggle = !this.commentToggle;
  }

  commentOnPost() {
    this.commentService.createCom(this.commentInput, this.post?.id);
  }
}
