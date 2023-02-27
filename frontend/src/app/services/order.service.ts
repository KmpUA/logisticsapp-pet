import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Order } from '../models/order';
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';
import { OrderResponse } from '../models/order-response';

const ORD_API = environment.API_URL + 'orders';
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

  getOrderById(id: number){
    return this.http.get<OrderResponse>(ORD_API + '/' + id);
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
}
