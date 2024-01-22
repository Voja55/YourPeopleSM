import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./authentification/auth.service";
import {Observable} from "rxjs";
import {CreateCommentPayload} from "../model/payloads/createComment.payload";
import {CommentPayload} from "../model/payloads/comment.payload";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

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

  createCom(comment: CreateCommentPayload, postId: number | undefined): Observable<any> {
    return this.httpClient.post<any>('http://localhost:8080/create/onPost/' + postId, comment, this.options());
  }

  createReplie(replie: CreateCommentPayload, postId: number | undefined): Observable<any> {
    return this.httpClient.post<any>('http://localhost:8080/create/onCom/' + postId, replie, this.options());
  }

  getReplies(commentId: number | undefined): Observable<CommentPayload[]>{
    return this.httpClient.get<CommentPayload[]>('http://localhost:8080/replies/' + commentId, this.options());
  }

  getComments(postId: number | undefined): Observable<CommentPayload[]>{
    return this.httpClient.get<CommentPayload[]>('http://localhost:8080/comments/' + postId, this.options());
  }

}
