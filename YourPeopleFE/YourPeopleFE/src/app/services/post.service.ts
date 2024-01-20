import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./authentification/auth.service";
import {Observable} from "rxjs";
import {Post} from "../model/post";
import {NewPostPayload} from "../model/payloads/newPost.payload";
import {PostDisplayPayload} from "../model/payloads/postDisplay.payload";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(
    private httpClient: HttpClient,
    private authService: AuthService
  ) { }

  options(){
    return{
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.authService.getToken()}`
      })
    }
  }

  getMyPosts(): Observable<PostDisplayPayload[]> {
    return this.httpClient.get<PostDisplayPayload[]>('http://localhost:8080/userPosts', this.options());
  }
  getHomePosts(): Observable<Post[]> {
    return this.httpClient.get<Post[]>('http://localhost:8080/home', this.options());
  }
  getGroupPosts(groupId: number): Observable<Post[]> {
    return this.httpClient.get<Post[]>('http://localhost:8080/group/' + groupId + '/posts', this.options());
  }
  postCreatePost(groupId: number, postCreate: NewPostPayload): Observable<any> {
    return this.httpClient.post('http://localhost:8080/community/' + groupId + '/create', postCreate, this.options());
  }

  getPost(postId: number): Observable<Post> {
    return this.httpClient.get<Post>('http://localhost:8080/post/' + postId);
  }

}
