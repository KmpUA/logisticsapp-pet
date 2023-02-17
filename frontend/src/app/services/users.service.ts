import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/User';
import { environment } from 'src/environments/environment';

const API_URL = environment.API_URL;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class UsersService {
  constructor(private http: HttpClient) { }

  getUsers(page: number = -1): Observable<User[]> {
    // delete this if else, leave only else
    if (page == -1)
      return this.http.get<User[]>(API_URL + 'users');
    else
      return this.http.get<User[]>(API_URL + 'users?page=' + page);
  }

  updateRole(id: number, role: string): Observable<any> {
    return this.http.patch(API_URL + 'users/' + id.toString(), role, httpOptions);
  }

  updateStatus(id: number, status: string): Observable<any> {
    return this.http.patch(API_URL + 'users/' + id.toString(), status, httpOptions);
  }

  addUser(user: User): Observable<any> {
    return this.http.post(API_URL + 'users/', user, httpOptions);
  }
}
