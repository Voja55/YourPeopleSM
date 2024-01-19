import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../services/authentification/auth.service";
import {RegisterRequest} from "../../../model/auth/register-request";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{

  registerPayload: RegisterRequest;
  registerForm!: FormGroup;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {
    this.registerPayload = {
      username: '',
      password: '',
      email:  '',
      name: '',
      surname: '',
      description:  ''
    };
  }

  showEmptyInputMessage: boolean = false;
  passMatch: boolean = false;

  ngOnInit() {
    this.registerForm = new FormGroup({
      name: new FormControl('', Validators.required),
      surname: new FormControl('', Validators.required),
      username: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      description: new FormControl(''),
      password1: new FormControl('', Validators.required),
      password2: new FormControl('', Validators.required)
    })
  }

  register() {
    this.registerPayload.name = this.registerForm.get('name')?.value;
    this.registerPayload.surname = this.registerForm.get('surname')?.value;
    this.registerPayload.username = this.registerForm.get('username')?.value;
    this.registerPayload.email = this.registerForm.get('email')?.value;
    this.registerPayload.description = this.registerForm.get('description')?.value;
    let password1 = this.registerForm.get('password1')?.value;
    let password2 = this.registerForm.get('password2')?.value;
    if(password1 != password2){
      this.passMatch = true;
    }
    this.registerPayload.password = password1;

    this.authService.register(this.registerPayload).subscribe(data => {
      this.router.navigate(['/login'],
        {queryParams: {registered: true} });
    }, error=>{
      console.log(error);
    })

  }
}
