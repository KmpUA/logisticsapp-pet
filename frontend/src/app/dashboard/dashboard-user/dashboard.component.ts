import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';
import { CityService } from 'src/app/services/city.service';
import { MatDialog } from '@angular/material/dialog';
import { FormGroup, FormControl } from '@angular/forms';
import { Cities } from 'src/app/models/cities';
import { Trucker } from 'src/app/models/trucker';
import { TruckerService } from 'src/app/services/trucker.service';
import { AuthService } from 'src/app/services/auth.service';
import { OrderResponse } from 'src/app/models/order-response';
import { Customer } from 'src/app/models/customer';


@Component({
  selector: 'app-dashboard',
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  orderList: OrderResponse[] = [];
  cityList: Cities[] = [];
  truckerList: Trucker[] = [];
  orderList1: OrderResponse = new OrderResponse();
  customer: Customer = new Customer;
  constructor(public ord: OrderService, public cit: CityService, public tru: TruckerService, private dialog: MatDialog, public auth: AuthService) { }


  ngOnInit(): void {
    this.cit.getCities().subscribe((response: Cities[]) => {
      this.cityList = response;
      console.log(this.cityList)
      localStorage.setItem("cities", JSON.stringify(this.cityList))
    });
    console.log('user',this.customer)
    
    this.ord.getCustomer(this.auth.user.id!).subscribe((response: Customer) => {
      this.customer = response;
      if(this.customer.orders != null)
      this.orderList = this.customer.orders;
      console.log(this.customer)
      })

    this.tru.getTrucker().subscribe((response: Trucker[]) => {
      this.truckerList = response;
      console.log(response)
      localStorage.setItem("truckers", JSON.stringify(this.truckerList))
    });

  }

  openDialog() {
    const dialogRef = this.dialog.open(DialogContentExampleDialog);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });

  }
  getCityName(id: undefined | number) {
    if (id) {
      return this.cit.getCity(id)?.cityName;

    }
    return {}
  }

  getTruckerName(trucker: Trucker | number | undefined | null) {
    if (trucker) {
      return (trucker as Trucker).firstName;
    }
    return []
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
    customer: new FormControl(''),
    startDeliver: new FormControl(''),
    endDeliver: new FormControl('')
  });

  constructor(private ord: OrderService, private tru: TruckerService, private user: AuthService) { }

  cityList: Cities[] = [];

  ngOnInit(): void {
    this.cityList = JSON.parse(localStorage.getItem("cities") || '{}');

  }
  saveOrder(orderForm: FormGroup) {

    if (this.orderForm.valid == true) {
      console.log(this.orderForm);
    }

    if (this.user.user)
      orderForm.value.customer = this.user.user.id;

    this.ord.postOrder(orderForm.value).subscribe(()=>{window.location.reload()})

  }

};

