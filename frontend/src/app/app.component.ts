import { Component } from '@angular/core';
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
  constructor(
    public authService: AuthService,
    private eventBusService: EventBusService) { }

  ngOnInit(): void {

    this.eventBusSub = this.eventBusService.on('logout', () => {
      this.signOut();
    });

    this.eventBusSub = this.eventBusService.on('refresh', () => {
      this.refresh();
    })
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
}
