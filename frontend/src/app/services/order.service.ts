import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Order } from '../models/order';
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';
import { OrderResponse } from '../models/order-response';
import { Customer } from '../models/customer';

const ORD_API = environment.API_URL + 'orders';
const ORD_API1 = environment.API_URL + 'customers';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class OrderService {

  constructor(private http: HttpClient) { }
  getOrders(): Observable<OrderResponse[]> {
    return this.http.get<OrderResponse[]>(ORD_API + '/all');
  }

  getCustomer(id: number){
    return this.http.get<Customer>(ORD_API1 + '/' + id);
  }

  updateOrder(order: OrderResponse): Observable<any> {
    return this.http.put(ORD_API + '/' + order.id, order, httpOptions);
  }

  deleteOrder(id: number) {
    return this.http.delete(ORD_API + '/' + id);
  }

  postOrder(order: Order) {
    return this.http.post(
      ORD_API, order, httpOptions
    )
  }
  getTruckerOrders(truckerId: number): Observable<any> {
    return this.http.get(ORD_API + '/trucker_id/' + truckerId);
  }

  completeOrder(order: Order): Observable<any> {
    return this.http.put(ORD_API + '/' + order.id, order);
  }

  deleteOrder(orderId: number): Observable<any> {
    return this.http.delete(API_URL + '/' + orderId);
  }
}
