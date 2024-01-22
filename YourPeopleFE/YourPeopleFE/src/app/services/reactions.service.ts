import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./authentification/auth.service";
import {CreateReactionPayload} from "../model/payloads/createReaction.payload";

@Injectable({
  providedIn: 'root'
})
export class ReactionsService {

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

  reactOnPost(reaction: CreateReactionPayload, postid: number | undefined){
    return this.httpClient.post('http://localhost:8080/react/post/' + postid, reaction, this.options());
  }
  reactOnComment(reaction: CreateReactionPayload, commentid: number){
    return this.httpClient.post('http://localhost:8080/react/comment/' + commentid, reaction, this.options());
  }
}
