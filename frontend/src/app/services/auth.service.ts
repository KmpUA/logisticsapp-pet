import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import { Router } from '@angular/router';

const AUTH_API = environment.API_URL + 'auth/';
const USER_KEY = 'user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  get user(): User {
    let user = localStorage.getItem(USER_KEY);
    if (user)
      return JSON.parse(user);
    return {};
  }

  constructor(
    private http: HttpClient
  ) { }

  login(email: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'authenticate', {
      email,
      password
    }, httpOptions);
  }

  refreshToken() {
    return this.http.post(AUTH_API + 'refresh', null, httpOptions);
  }

  signOut(): Observable<any> {
    localStorage.removeItem(USER_KEY);

    return this.http.post(AUTH_API + 'logout', null, httpOptions);
  }

  saveUser(user: User): void {
    localStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem(USER_KEY);
  }
}
