import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./authentification/auth.service";
import {Observable} from "rxjs";
import {GroupPayload} from "../model/payloads/group.payload";

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
}
