import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthService } from '../services/auth.service';
import { EventBusService } from '../services/event-bus.service';
import { environment } from 'src/environments/environment';
import { EventData } from '../models/event';
import { Router } from '@angular/router';

const AUTH_API = environment.API_URL + 'auth/authenticate';

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService,
    private eventBusService: EventBusService,
    private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    req = req.clone({
      withCredentials: true,
    });

    return next.handle(req).pipe(
      catchError((error) => {
        if (
          error instanceof HttpErrorResponse &&
          error.status === 401
        ) {
          return this.handle401Error(req, next);
        }

        return throwError(() => error);
      })
    );
  }

  private handle401Error(request: HttpRequest<any>, next: HttpHandler) {
      if (this.authService.isLoggedIn()) {
        this.authService.refreshToken().subscribe({
          next: res => {
            console.log(res);
            this.eventBusService.emit(new EventData('dashboard', null));
          },
          error: err => {
            console.log(err);
            this.eventBusService.emit(new EventData('logout', null));
          }
        });
      }
    return next.handle(request);
  }
}

export const HttpInterceptorProvider = {
  provide: HTTP_INTERCEPTORS,
  useClass: HttpRequestInterceptor,
  multi: true
};
