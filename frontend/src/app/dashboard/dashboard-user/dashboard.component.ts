import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';
import { Order } from 'src/app/models/order';
import { MatDialog } from '@angular/material/dialog';
import { FormGroup, FormControl } from '@angular/forms';
import { Cities } from 'src/app/models/cities';
import { Trucker } from 'src/app/models/trucker';



@Component({
  selector: 'app-dashboard',
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  orderList: Order[] = [];


  constructor(private api: OrderService, private dialog: MatDialog) { }


  ngOnInit(): void {
    this.api.getOrders().subscribe((response: Order[]) => {
      this.orderList = response;
      console.log(response)
    });
  }

  openDialog() {
    const dialogRef = this.dialog.open(DialogContentExampleDialog);


    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });


  }

  deleteOrder(id: number) {
    //Delete
    {
      this.api.deleteOrder(id)
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

  constructor(private api: OrderService) { }
  cityList: Cities[] = [];
  truckerList: Trucker[] = [];
  order: Order = new Order();

  ngOnInit(): void {

    this.api.getCities().subscribe((response: Cities[]) => {
      this.cityList = response;

      this.api.getTrucker().subscribe((res: Trucker[]) => {
        this.truckerList = res;
      })

    });
  }
  saveOrder(orderForm: FormGroup) {

    if (this.orderForm.valid == true) {
      console.log(this.orderForm);
    }

    this.api.postOrder(orderForm.value).subscribe()
  }


};

