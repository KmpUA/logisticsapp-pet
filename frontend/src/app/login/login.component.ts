import { Component } from '@angular/core';
import { Router } from '@angular/router';
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

  constructor(private authService: AuthService, private _router: Router) { }

  onSubmit() {
    this.authService.login(this.email, this.password).subscribe({
      next: response => {
        this.authService.saveUser(response);
        this.isLoginFailed = false;
        this.errorMessage = '';
        if (this.authService.user.role === "TRUCKER") {
          this._router.navigateByUrl('/dashboard-trucker')
        }
        if (this.authService.user.role === "CUSTOMER") {
          this._router.navigateByUrl('/customer')

        }
        if (this.authService.user.role === "DISPATCHER") {
          this._router.navigateByUrl('/dispatcher')
        }
        if (this.authService.user.role === "ADMIN") {
          this._router.navigateByUrl('/dashboard-admin')

        }
      },
      error: err => {
         this.errorMessage = "Invalid login or password"
         this.isLoginFailed = true;
      }
      });
  }
}
