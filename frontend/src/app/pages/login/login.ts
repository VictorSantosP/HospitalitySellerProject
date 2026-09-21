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
  selector: 'app-login',
  styleUrl: './login.css',
  templateUrl: './login.html',
})
export class Login {
  loginForm!: FormGroup;

  constructor(){
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.min(6)])
    });
  }
}
