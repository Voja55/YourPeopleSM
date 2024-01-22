import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserPayload} from "../model/payloads/user.payload";
import {AuthService} from "./authentification/auth.service";
import {UpdateInfoPayload} from "../model/payloads/updateInfo.payload";
import {PassChangePayload} from "../model/payloads/passChange.payload";

@Injectable({
  providedIn: 'root'
})
export class UserService {

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

  getProfile(): Observable<UserPayload> {
    return this.httpClient.get<UserPayload>('http://localhost:8080/users/profile', this.options());
  }

  updateProfile(updateUserInfo: UpdateInfoPayload): Observable<UserPayload> {
    return this.httpClient.put<UserPayload>('http://localhost:8080/users/edit', updateUserInfo, this.options());
  }

  changePass(passChangePayload: PassChangePayload): Observable<any> {
    return this.httpClient.put<any>('http://localhost:8080/users/change-password', passChangePayload, this.options());
  }

  searchUsers(search: string): Observable<UserPayload[]> {
    return this.httpClient.get<UserPayload[]>('http://localhost:8080/users/search/'+ search, this.options());
  }

  findByUsername(username: string): Observable<UserPayload> {
    return this.httpClient.get<UserPayload>('http://localhost:8080/users/getuser/' + username, this.options());
  }

  getAllAdmin(): Observable<UserPayload[]> {
    return this.httpClient.get<UserPayload[]>('http://localhost:8080/users/all', this.options());
  }
}
