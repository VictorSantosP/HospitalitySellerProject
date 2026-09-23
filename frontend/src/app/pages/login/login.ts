import { Component } from '@angular/core';
import { DefaultLoginLayout } from '../../components/default-login-layout/default-login-layout';
import { ReactiveFormsModule, FormGroup, FormControl, Validators, Form } from '@angular/forms';
import { DefaultFormInput } from '../../components/default-form-input/default-form-input';
import { Router } from '@angular/router';
import { Signup } from '../signup/signup';
import { LoginService } from '../../services/login-service';

interface LoginForm {
  email: FormControl,
  password: FormControl
}

@Component({
  imports: [
    DefaultLoginLayout,
    ReactiveFormsModule,
    DefaultFormInput
  ],
  providers: [
    LoginService
  ],
  selector: 'app-login',
  styleUrl: './login.css',
  templateUrl: './login.html',
})
export class Login {
  loginForm!: FormGroup<LoginForm>;

  constructor(
    private router: Router,
    private loginService: LoginService
  ){
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.min(6)])
    });
  }

  submit(){
    this.loginService.
    login(this.loginForm.value.email, this.loginForm.value.password).
    subscribe({
      next: () => console.log("Sucess!"),
      error: () => console.log("Error! Try again later.")
    });
  }

  navigate(){
    this.router.navigate(["signup"]);
  }
}
