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
  constructor(private authService: AuthService, private _router: Router) { }
  onSubmit() {
    this.authService.login(this.email, this.password).subscribe(
      response => {
        this.authService.saveToken(response.token);
        this.authService.saveUser(response.userResponse);
        if (this.authService.user.role === "TRUCKER") {
          this._router.navigateByUrl('/trucker')
        }
        if (this.authService.user.role === "CUSTOMER") {
          this._router.navigateByUrl('/')

        }
        if (this.authService.user.role === "DISPATCHER") {
          this._router.navigateByUrl('/dispatcher')
        }
        if (this.authService.user.role === "ADMIN") {
          this._router.navigateByUrl('/admin')

        }
      })
    
    
  }
}
