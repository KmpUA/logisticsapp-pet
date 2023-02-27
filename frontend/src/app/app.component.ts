import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UserProfileComponent } from './dashboard/user-profile/user-profile.component';
import { Subscription } from 'rxjs';
import { AuthService } from './services/auth.service';
import { EventBusService } from './services/event-bus.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  eventBusSub?: Subscription;
  constructor(public authService: AuthService, public _router: Router, public dialog:MatDialog,
              private eventBusService: EventBusService) { }

  ngOnInit(): void {

    this.eventBusSub = this.eventBusService.on('logout', () => {
      this.signOut();
    });

    this.eventBusSub = this.eventBusService.on('refresh', () => {
      this.refresh();
    })
  }

  signOut() {
    this.authService.logout().subscribe({
      next: res => {
        console.log(res);

        window.location.reload();
      },
      error: err => {
        console.log(err);
      }
    });
  }

  refresh() {
    this.authService.refreshToken().subscribe({
      next: res => {
        console.log(res);

        window.location.reload();
      },
      error: err => {
        console.log(err);
      }
    });
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
      this._router.navigateByUrl('/dashboard-admin')

    }
  }
  openDialog() {
    const dialogRef = this.dialog.open(UserProfileComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });

  }
}
