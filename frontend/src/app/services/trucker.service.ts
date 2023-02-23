import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';
import { Trucker } from '../models/trucker';

const TRU_API = environment.API_URL + 'truckers';

@Injectable({
  providedIn: 'root'
})
export class TruckerService {

  constructor(private http:HttpClient) { }

  getTrucker(): Observable<Trucker[]> {
    return this.http.get<Trucker[]>(TRU_API + '/all')
  }
}
