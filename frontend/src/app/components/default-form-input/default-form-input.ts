import { Component, Input } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

type InputTypes = "text" | "email" | "password"

@Component({
  imports: [
    ReactiveFormsModule
  ],
  selector: 'app-default-form-input',
  styleUrl: './default-form-input.css',
  templateUrl: './default-form-input.html',
})
export class DefaultFormInput {
  @Input() type: InputTypes = "text";
  @Input() formName: string = "";
  @Input() placeholder: string = "";
  @Input() label: string = "";
}
