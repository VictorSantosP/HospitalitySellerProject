import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { DefaultLoginLayout } from './components/default-login-layout/default-login-layout';

@Component({
  imports: [
    RouterOutlet,
    DefaultLoginLayout
  ],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App {
  protected readonly title = signal('frontend');
}
