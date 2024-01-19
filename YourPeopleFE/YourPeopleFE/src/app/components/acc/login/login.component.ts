import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../services/authentification/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {throwError} from "rxjs";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{

  constructor(
    private authService: AuthService,
    private activatedRout: ActivatedRoute,
    private router: Router,
  ) {
  }

  showErrorMessage: boolean = false;
  showEmptyInputMessage: boolean = false;
  username: string = '';
  password: string = '';

  login() {
    let username = this.username;
    let password = this.password;
     if(username === '' || password === ''){
       this.showEmptyInputMessage = true
    } else {
       this.authService.login(username, password).subscribe(data => {
       this.router.navigateByUrl('/profile');
     }, error => {
       this.showErrorMessage = true;
       throwError(error);
     })}
  }
}
