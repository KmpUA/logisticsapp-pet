import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/models/order';
import { AuthService } from 'src/app/services/auth.service';
import { OrderService } from 'src/app/services/order.service';


@Component({
  selector: 'app-trucker-dashboard',
  templateUrl: './trucker-dashboard.component.html',
  styleUrls: ['./trucker-dashboard.component.css']
})
export class TruckerDashboardComponent implements OnInit {
  public orderList: Order[] = [];

  constructor(private orderService: OrderService, private authService: AuthService) { }

  ngOnInit() {
    this.orderService.getTruckerOrders(this.authService.user.id!).subscribe({
      next: response => {
        this.orderList = response;
      }
    })
  }

  toggleCompleted(order: Order) {
    this.orderService.completeOrder(order).subscribe();
  }
}
