import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./authentification/auth.service";
import {CreateGroupPayload} from "../model/payloads/createGroup.payload";
import {Observable} from "rxjs";
import {GroupPayload} from "../model/payloads/group.payload";
import {FriendPayload} from "../model/payloads/friend.payload";

@Injectable({
  providedIn: 'root'
})
export class FriendsService {

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

  postSendFriendReq(username: String | undefined): Observable<FriendPayload> {
    return this.httpClient.post<FriendPayload>('http://localhost:8080/users/friend/send/' + username, this.options());
  }

  acceptFriendReq(friendReqId: number): Observable<FriendPayload> {
    return this.httpClient.post<FriendPayload>('http://localhost:8080/users/friend/accept' + friendReqId, this.options());
  }

  declineFriendReq(friendReqId: number): Observable<FriendPayload> {
    return this.httpClient.post<FriendPayload>('http://localhost:8080/users/friend/decline' + friendReqId, this.options());
  }

  getFriends(): Observable<FriendPayload[]> {
    return this.httpClient.get<FriendPayload[]>('http://localhost:8080/users/friends', this.options());
  }

  getWaiting(): Observable<FriendPayload[]> {
    return this.httpClient.get<FriendPayload[]>('http://localhost:8080/users/friends/waiting', this.options());
  }

}
