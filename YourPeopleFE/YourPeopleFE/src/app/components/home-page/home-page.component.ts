import {Component, OnInit} from '@angular/core';
import {throwError} from "rxjs";
import {PostDisplayPayload} from "../../model/payloads/postDisplay.payload";
import {PostService} from "../../services/post.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit{
  posts: PostDisplayPayload[];

  constructor(
    private postService: PostService
  ) {
  }

  ngOnInit(): void {
    this.postService.getHomePosts().subscribe(data => {
      console.log(data);
      this.posts = data;
    }, error => {
      throwError(error);
    });
  }

}
