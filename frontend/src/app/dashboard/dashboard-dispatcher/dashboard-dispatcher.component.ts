import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';
import { MatDialog } from '@angular/material/dialog';
import { Order } from 'src/app/models/order';
import { Trucker } from 'src/app/models/trucker';


@Component({
  selector: 'app-dashboard-dispatcher',
  templateUrl: 'dashboard-dispatcher.component.html',
  styleUrls: ['dashboard-dispatcher.component.css']

})
export class DashboardDispatcherComponent implements OnInit {

  orderList: Order[] = [];
  truckerList: Trucker[] = [];

  constructor(private api: OrderService) { }

  ngOnInit(): void {
    this.api.getOrders().subscribe((response: Order[]) => {
      this.orderList = response;
    });
    this.api.getTrucker().subscribe((response: Trucker[]) => {
      this.truckerList = response;
      console.log(response)
    });
  }
  assignTrucker(truckerId: number, order: Order) {
    order.trucker = truckerId;
    this.api.updateOrder(order).subscribe();
  }

}

