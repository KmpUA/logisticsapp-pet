import { EventBusService } from './../services/event-bus.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EventData } from '../models/event';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  isLoginFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService, private _router: Router, private eventBus: EventBusService) { }

  onSubmit() {
    this.authService.login(this.email, this.password).subscribe({
      next: response => {
        this.authService.saveUser(response);
        this.isLoginFailed = false;
        this.errorMessage = '';
        this.eventBus.emit(new EventData('dashboard', null));
      },
      error: err => {
        console.log(err);
         this.errorMessage = "Invalid login or password"
         this.isLoginFailed = true;
      }
      });
  }
}
