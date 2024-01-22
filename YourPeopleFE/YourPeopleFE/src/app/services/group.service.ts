import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./authentification/auth.service";
import {Observable} from "rxjs";
import {GroupPayload} from "../model/payloads/group.payload";
import {CreatePostPayload} from "../model/payloads/createPost.payload";
import {CreateGroupPayload} from "../model/payloads/createGroup.payload";
import {RequestPayload} from "../model/payloads/request.payload";

@Injectable({
  providedIn: 'root'
})
export class GroupService {

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
  getMyGroups(): Observable<GroupPayload[]> {
    return this.httpClient.get<GroupPayload[]>('http://localhost:8080/group/yourGroups', this.options());
  }
  getUserGroups(username:String): Observable<GroupPayload[]> {
    return this.httpClient.get<GroupPayload[]>('http://localhost:8080/group/groupsBy/'+username, this.options());
  }

  getJoinedGroups(): Observable<GroupPayload[]> {
    return this.httpClient.get<GroupPayload[]>('http://localhost:8080/group/joinedGroups', this.options());
  }
  getUserMemberOf(username:String): Observable<GroupPayload[]> {
    return this.httpClient.get<GroupPayload[]>('http://localhost:8080/group/userMemberOf/'+username, this.options());
  }

  getGroup(id: number): Observable<GroupPayload> {
    return this.httpClient.get<GroupPayload>('http://localhost:8080/group/'+id, this.options());
  }

  getExploreGroups(): Observable<GroupPayload[]> {
    return this.httpClient.get<GroupPayload[]>('http://localhost:8080/group/foryou', this.options());
  }

  getAdminGroups(): Observable<GroupPayload[]> {
    return this.httpClient.get<GroupPayload[]>('http://localhost:8080/group/all', this.options());
  }

  postCreateGroup(groupCreate: CreateGroupPayload): Observable<GroupPayload> {
    return this.httpClient.post<GroupPayload>('http://localhost:8080/group/create', groupCreate, this.options());
  }

  suspendGroup(groupId: number, reason: string): Observable<any> {
    return this.httpClient.post('http://localhost:8080/group/suspend' + groupId, reason, this.options());
  }


  waitingGroupReqs(): Observable<RequestPayload[]> {
    console.log("header:")
    console.log(this.options().headers);
    return this.httpClient.get<RequestPayload[]>('http://localhost:8080/group/requests/waiting', this.options());
  }

  acceptGroupReq(id: number): Observable<any> {
    console.log(this.options().headers);
    return this.httpClient.put('http://localhost:8080/group/request/accept' + id,null, this.options());
  }

  declineGroupReq(id: number) {
    return this.httpClient.put('http://localhost:8080/group/request/reject' + id, null, this.options());
  }

  sendGroupReq(id: number) {
    return this.httpClient.post('http://localhost:8080/group/request'+id, null, this.options());
  }
}
