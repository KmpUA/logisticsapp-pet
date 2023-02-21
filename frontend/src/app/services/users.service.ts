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

  getUsers(page: number): Observable<any> {
    return this.http.get<User[]>(API_URL + 'users?page=' + page);
  }

  put(user: User): Observable<any> {
    return this.http.put(API_URL + 'users/' + user.id!.toString(), user, httpOptions);
  }

  addUser(user: User): Observable<any> {
    user.status = "ACTIVE";
    return this.http.post(API_URL + 'users', user, httpOptions);
  }
}
