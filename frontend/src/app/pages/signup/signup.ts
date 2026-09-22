import { Component } from '@angular/core';
import { DefaultLoginLayout } from '../../components/default-login-layout/default-login-layout';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { DefaultFormInput } from '../../components/default-form-input/default-form-input';

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
  signupForm!: FormGroup;

  constructor(){
    this.signupForm = new FormGroup({
      username: new FormControl('', [Validators.required, Validators.minLength(3)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.min(6)]),
      passwordConfirm: new FormControl('', [Validators.required, Validators.minLength(6)])
    });
  }
}
