import { Component } from '@angular/core';
import { Order } from 'src/app/models/order';
import { OrderResponse } from 'src/app/models/order-response';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {
  public orderList: OrderResponse[] = [];
  constructor(private orderService: OrderService) { }

  ngOnInit() {
    this.orderService.getOrders().subscribe({
      next: response => {
        this.orderList = response;
      }
    })
  }

  deleteOrder(orderId: number) {
    this.orderService.deleteOrder(orderId).subscribe();
  }
}
