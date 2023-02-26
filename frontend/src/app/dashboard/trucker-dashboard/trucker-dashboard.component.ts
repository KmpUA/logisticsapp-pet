import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/models/order';
import { OrderService } from 'src/app/services/order.service';


@Component({
  selector: 'app-trucker-dashboard',
  templateUrl: './trucker-dashboard.component.html',
  styleUrls: ['./trucker-dashboard.component.css']
})
export class TruckerDashboardComponent implements OnInit {
  public orderList: Order[] = [
    {
      created: "123.123213",
      cityFrom: "cityFrom",
      cityTo: "cityTo",
      startDeliver: "startDeliver",
      endDeliver: "endDeliver",
      cargoDescription: "asdfasfd safddsaf sa fdsadfsadfsad fads fsadfasdf sadf saf safdadsf dsaf sdaf sadf\
      asdfsad fds fasdfsafddsafdsafasd fdsafasd fdsaf",
      trucker: 1,

    },
    {
      created: "123.123213",
      cityFrom: "cityFrom",
      cityTo: "cityTo",
      startDeliver: "startDeliver",
      endDeliver: "endDeliver"
    }];

  constructor(private orderService: OrderService) { }

  ngOnInit() {
    this.orderService.getOrders().subscribe({
      next: response => {
        this.orderList = response;
      }
    })
  }

}
