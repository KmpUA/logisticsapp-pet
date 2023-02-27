import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';
import { Trucker } from 'src/app/models/trucker';
import { TruckerService } from 'src/app/services/trucker.service';
import { CityService } from 'src/app/services/city.service';
import { OrderResponse } from 'src/app/models/order-response';
import { User } from 'src/app/models/User';

@Component({
  selector: 'app-dashboard-dispatcher',
  templateUrl: 'dashboard-dispatcher.component.html',
  styleUrls: ['dashboard-dispatcher.component.css']

})
export class DashboardDispatcherComponent implements OnInit {

  orderList: OrderResponse[] = [];
  truckerList: Trucker[] = [];

  constructor(public ord: OrderService, public tru: TruckerService, public cit: CityService) { }

  ngOnInit(): void {
    this.ord.getOrders().subscribe((response: OrderResponse[]) => {
      this.orderList = response;
    });
    this.tru.getTrucker().subscribe((response: Trucker[]) => {
      this.truckerList = response;
      console.log(response)
    });
  }

  getTruckerName(trucker: Trucker | number | undefined | null) {
    if (trucker) {
      return (trucker as Trucker).firstName;
    }
    return []
  }

  getCityName(id: undefined | number) {
    if (id) {
      return this.cit.getCity(id)?.cityName;

    }
    return {}
  }

  assignTrucker(truckerId: number, order: OrderResponse) {
    order.trucker = truckerId;
    order.customer = (order.customer as User).id;
    //order.customer = order.customer.id;
    this.ord.updateOrder(order).subscribe();
  }

}

