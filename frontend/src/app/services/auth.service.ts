import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/User';
import { UsersService } from './users.service';

const AUTH_API = environment.API_URL + 'auth/';
const TOKEN_KEY = 'auth-token';
const USER_KEY = 'user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  get token() {
    return localStorage.getItem(TOKEN_KEY);
  }

  get user(): User {
    let user = localStorage.getItem(USER_KEY);
    if (user)
      return JSON.parse(user);
    return {};
  }

  constructor(
    private http: HttpClient,
    private userService: UsersService
  ) { }

  login(email: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'authenticate', {
      email,
      password
    }, httpOptions);
  }

  logout(): void {
    localStorage.removeItem(TOKEN_KEY);
  }

  saveToken(token: string): void {
    localStorage.setItem(TOKEN_KEY, token);
  }

  saveUser(user: User): void {
    localStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem(TOKEN_KEY);
  }
}
