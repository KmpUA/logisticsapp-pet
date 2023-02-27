import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cities } from '../models/cities';
import { environment } from 'src/environment/environment';

const CITY_API = environment.API_URL + 'cities';

@Injectable({
  providedIn: 'root'
})
export class CityService {

  constructor(private http: HttpClient) { }
  getCities(): Observable<Cities[]> {
    return this.http.get<Cities[]>(CITY_API + '/all')
  }
  getCity(id: number) {
    let citiesString = localStorage.getItem("cities");
    if (citiesString != null) {
      let cities: Cities[] = JSON.parse(citiesString);
      return cities.find(i => i.id === id)
    }
    return {}
  }
}
