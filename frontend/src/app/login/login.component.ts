import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  constructor(private authService: AuthService) { }
  onSubmit() {
    this.authService.login(this.username, this.password).subscribe(
      response => {
        this.authService.saveToken(response.access_token);
        console.log(response.access_token);
      }
    )
  }
}
