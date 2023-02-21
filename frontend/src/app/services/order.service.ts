import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators'
import { Order } from '../models/order';
import { Observable } from 'rxjs';
import { Cities } from '../models/cities';
import { environment } from 'src/environment/environment';
import { Trucker } from '../models/trucker';

const ORD_COU_API = environment.ORD_COU_API;
// const ORD_COU_API1 = "http://localhost:4200/api/orders";
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class OrderService {



  constructor(private http: HttpClient) { }
  getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(ORD_COU_API + 'orders/all');
  }
  getCities(): Observable<Cities[]> {
    return this.http.get<Cities[]>(ORD_COU_API + 'cities/all')
  }
  getTrucker(): Observable<Trucker[]> {
    return this.http.get<Trucker[]>(ORD_COU_API + 'trucker/all')
  }
  deleteOrder(id: number) {
    return this.http.delete(ORD_COU_API + 'orders/' + id);
  }
  postOrder(order: Order) {
    //const body = {cityFrom: order.cityFrom, cityTo: order.cityTo, cargoDescription: order.cargoDescription, cargoSize: order.cargoSize,cargoWeight: order.cargoWeight, }
    return this.http.post(
      ORD_COU_API + 'orders', order, httpOptions
    )
  }
  // postTrucker(trucker: Order) {
  //   //const body = {cityFrom: order.cityFrom, cityTo: order.cityTo, cargoDescription: order.cargoDescription, cargoSize: order.cargoSize,cargoWeight: order.cargoWeight, }
  //   return this.http.post(
  //     'http://localhost:8080/api/trucker', trucker, httpOptions
  //   )
  // }
}
