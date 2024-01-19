import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {JwtUtilsService} from "./jwt-utils.service";
import {BehaviorSubject, map, Observable} from "rxjs";
import {RegisterRequest} from "../../model/auth/register-request";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private httpClient: HttpClient,
    private jwtUtilService: JwtUtilsService){
  }

  login(username: string, password: string): Observable<boolean> {
    let headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
    return this.httpClient.post<boolean>('http://localhost:8080/users/login',
      JSON.stringify({username, password}),{headers})
      .pipe(map((res: any) => {
        return this.setTokenResponse(res);
      }))
  }

  setTokenResponse(res: String) {
    // @ts-ignore
    let token = res && res['accessToken'];
    if (token) {
      sessionStorage.setItem('currentUser', JSON.stringify({
        token: token
      }));
      return true
    }else {
      return false
    }
  }

  setToken(token: String) {
    sessionStorage.setItem('currentUser', JSON.stringify({
      token: token
    }));
    return true
  }

  getToken(): string{
    const sessionStorageUser = sessionStorage.getItem("currentUser")
    if (sessionStorageUser) {
      const currentUser = JSON.parse(sessionStorageUser);
      const token = currentUser && currentUser.token;
      return token ? token : "";
    }
    return "";
  }



  isLoggedIn(): boolean {
    if (this.getToken() != '') return true
    else return false
  }

  register(regReqPayload: RegisterRequest): Observable<any> {
    return this.httpClient.post("http://localhost:8080/users/registration", regReqPayload, {responseType: 'text'})
  }

  logout(){
    sessionStorage.removeItem('currentUser');
    location.href = "/login";
  }

  getCurrentUser() {
    const localStorageUser = sessionStorage.getItem("currentUser")
    if (localStorageUser) {
      return JSON.parse(localStorageUser);
    } else {
      return undefined;
    }
  }

  hasRole(role: string): boolean{
    //let jwtUtils: JwtUtilsService = new JwtUtilsService();
    const token = sessionStorage.getItem("currentUser");
    if(token){
      const currentRole = this.jwtUtilService.getRole(token);
      if (role === currentRole) {
        return true;
      }
    }
    return false;
  }
}
