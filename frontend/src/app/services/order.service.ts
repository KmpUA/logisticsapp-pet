import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Order } from '../models/order';

const API_URL = environment.API_URL + 'orders'

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  getOrders(): Observable<any> {
    return this.http.get(API_URL + '/all');
  }

  getTruckerOrders(truckerId: number): Observable<any> {
    return this.http.get(API_URL + '/trucker_id/' + truckerId);
  }

  completeOrder(order: Order): Observable<any> {
    return this.http.put(API_URL + '/' + order.id, order);
  }
}
