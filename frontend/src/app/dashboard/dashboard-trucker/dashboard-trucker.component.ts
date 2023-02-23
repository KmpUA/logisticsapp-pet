import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';
import { Order } from 'src/app/models/order';
import { Cities } from 'src/app/models/cities';
import { Trucker } from 'src/app/models/trucker';
import { TruckerService } from 'src/app/services/trucker.service';
import { CityService } from 'src/app/services/city.service';

@Component({
  selector: 'app-dashboard-trucker',
  templateUrl: './dashboard-trucker.component.html',
  styleUrls: ['./dashboard-trucker.component.css']
})
export class DashboardTruckerComponent implements OnInit {
  orderList: Order[] = [];
  cityList: Cities[] = [];
  truckerList: Trucker[] = [];

  constructor(public ord: OrderService, public tru: TruckerService, public cit: CityService) { }

  ngOnInit(): void {
    this.cit.getCities().subscribe((response: Cities[]) => {
      this.cityList = response;
      console.log(this.cityList)
      localStorage.setItem("cities", JSON.stringify(this.cityList))
    });
    this.ord.getOrders().subscribe((response: Order[]) => {
      this.orderList = response;
      console.log(response)
    });
    this.tru.getTrucker().subscribe((response: Trucker[]) => {
      this.truckerList = response;
      console.log(response)
    });
  }

  getCityName(id: undefined | number) {
    if (id) {
      return this.cit.getCity(id)?.cityName;
    }
    return {}
  }

  getTruckerName(id: undefined | number) {
    return this.truckerList.find(trucker => trucker.id === id)?.firstName;
  }
  toggle = true;
  status = 'Enable';

  compliteOrder(id: number) {
 
    this.toggle = !this.toggle;
    this.status = this.toggle ? 'Enable' : 'Disable';
  }
}