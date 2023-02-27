import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UserProfileComponent } from './dashboard/user-profile/user-profile.component';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  constructor(public authService: AuthService, public _router: Router, public dialog:MatDialog) { }
  signOut() {
    this.authService.logout();
  }
  goToDashboard() {
    if (this.authService.user.role === "TRUCKER") {
      this._router.navigateByUrl('/dashboard-trucker')
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
  }
  openDialog() {
    const dialogRef = this.dialog.open(UserProfileComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });

  }
}
