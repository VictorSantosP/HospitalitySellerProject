import { Component } from '@angular/core';
import { DefaultLoginLayout } from '../../components/default-login-layout/default-login-layout';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { DefaultFormInput } from '../../components/default-form-input/default-form-input';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login-service';

interface SignupForm {
  name: FormControl
  email: FormControl,
  password: FormControl,
  passwordConfirm: FormControl
}

@Component({
  imports: [
    DefaultLoginLayout,
    ReactiveFormsModule,
    DefaultFormInput
  ],
  selector: 'app-signup',
  styleUrl: './signup.css',
  templateUrl: './signup.html',
})
export class Signup {
  signupForm!: FormGroup<SignupForm>;

  constructor(
    private router: Router,
    private loginService: LoginService
  ) {
    this.signupForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(3)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.min(6)]),
      passwordConfirm: new FormControl('', [Validators.required, Validators.minLength(6)])
    });
  }

  submit() {
    this.loginService.
      login(this.signupForm.value.email, this.signupForm.value.password).
      subscribe({
        next: () => console.log("Sucess!"),
        error: () => console.log("Error! Try again later.")
      });
  }

  navigate() {
    this.router.navigate(["signup"]);
  }
}
