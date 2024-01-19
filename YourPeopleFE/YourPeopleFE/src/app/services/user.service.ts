import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserPayload} from "../model/payloads/user.payload";
import {AuthService} from "./authentification/auth.service";

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
        // 'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.authService.getToken()}`
      })
    }
  }

  getProfile(): Observable<UserPayload> {
    return this.httpClient.get<UserPayload>('http://localhost:8080/users/profile', this.options());
  }
}
