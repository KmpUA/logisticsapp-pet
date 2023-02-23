import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';
import { CityService } from 'src/app/services/city.service';
import { Order } from 'src/app/models/order';
import { MatDialog } from '@angular/material/dialog';
import { FormGroup, FormControl } from '@angular/forms';
import { Cities } from 'src/app/models/cities';
import { Trucker } from 'src/app/models/trucker';
import { TruckerService } from 'src/app/services/trucker.service';



@Component({
  selector: 'app-dashboard',
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  orderList: Order[] = [];
  cityList: Cities[] = [];
  truckerList: Trucker[] = [];

  constructor(public ord: OrderService, public cit: CityService,public tru: TruckerService, private dialog: MatDialog) { }


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

  openDialog() {
    const dialogRef = this.dialog.open(DialogContentExampleDialog);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });

  }
  getCityName(id:undefined|number) {
    if (id) {
      return this.cit.getCity(id)?.cityName;

    }
  return {}
  }
  
  getTruckerName(id: undefined | number) {
    return this.truckerList.find(trucker => trucker.id === id)?.firstName;
  }

  deleteOrder(id: number) {
    {
      this.ord.deleteOrder(id)
        .subscribe(response => {
          this.orderList = this.orderList.filter(item => item.id !== id);
        });
    }
  }
}

@Component({
  selector: 'dialog-content-example-dialog',
  templateUrl: './dialog-content-example-dialog.html',
})

export class DialogContentExampleDialog {

  orderForm: FormGroup = new FormGroup({
    cityFrom: new FormControl(''),
    cityTo: new FormControl(''),
    cargoSize: new FormControl(''),
    cargoWeight: new FormControl(''),
    cargoDescription: new FormControl(''),
  });

  constructor(private ord: OrderService, private tru: TruckerService) { }
  truckerList: Trucker[] = [];
  order: Order = new Order();
  cityList: Cities[] = [];

  ngOnInit(): void {
    this.cityList = JSON.parse(localStorage.getItem("cities")||'{}');
    this.tru.getTrucker().subscribe((res: Trucker[]) => {
      this.truckerList = res;
    })

  }
  saveOrder(orderForm: FormGroup) {

    if (this.orderForm.valid == true) {
      console.log(this.orderForm);
    }

    this.ord.postOrder(orderForm.value).subscribe()
  }

  
};

