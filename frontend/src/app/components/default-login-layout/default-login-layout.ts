import { Component, Input } from '@angular/core';

@Component({
  imports: [],
  selector: 'app-default-login-layout',
  styleUrl: './default-login-layout.css',
  templateUrl: './default-login-layout.html',
})
export class DefaultLoginLayout {
  @Input() primaryBtnText: string = "";
  @Input() secondaryBtnText: string = "";
}
