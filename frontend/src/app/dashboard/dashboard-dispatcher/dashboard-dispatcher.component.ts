import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';
import { Order } from 'src/app/models/order';
import { Trucker } from 'src/app/models/trucker';
import { TruckerService } from 'src/app/services/trucker.service';
import { CityService } from 'src/app/services/city.service';

@Component({
  selector: 'app-dashboard-dispatcher',
  templateUrl: 'dashboard-dispatcher.component.html',
  styleUrls: ['dashboard-dispatcher.component.css']

})
export class DashboardDispatcherComponent implements OnInit {

  orderList: Order[] = [];
  truckerList: Trucker[] = [];

  constructor(public ord: OrderService, public tru: TruckerService, public cit: CityService) { }

  ngOnInit(): void {
    this.ord.getOrders().subscribe((response: Order[]) => {
      this.orderList = response;
    });
    this.tru.getTrucker().subscribe((response: Trucker[]) => {
      this.truckerList = response;
      console.log(response)
    });
  }

  getTruckerName(id:undefined|number) {
    return this.truckerList.find(trucker => trucker.id === id)?.firstName;
  }

  getCityName(id: undefined | number) {
    if (id) {
      return this.cit.getCity(id)?.cityName;

    }
    return {}
  }
  
  assignTrucker(truckerId: number, order: Order) {
    order.trucker = truckerId;
    this.ord.updateOrder(order).subscribe();
  }

}

