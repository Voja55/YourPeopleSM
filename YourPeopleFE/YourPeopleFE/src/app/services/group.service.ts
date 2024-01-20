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
}
