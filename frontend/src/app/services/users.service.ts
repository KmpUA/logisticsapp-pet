import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/User';
import { environment } from 'src/environments/environment';

const USERS_API_URL = environment.API_URL + 'users';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class UsersService {
  public user: User = {};
  constructor(private http: HttpClient) { }

  getUsers(page: number): Observable<any> {
    return this.http.get<User[]>(USERS_API_URL + '?page=' + page);
  }

  updateUser(user: User): Observable<any> {
    return this.http.put(USERS_API_URL + '/' + user.id!.toString(), user, httpOptions);
  }

  addUser(user: User): Observable<any> {
    user.status = "ACTIVE";
    return this.http.post(USERS_API_URL, user, httpOptions);
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete(USERS_API_URL + '/' + id);
  }
}
