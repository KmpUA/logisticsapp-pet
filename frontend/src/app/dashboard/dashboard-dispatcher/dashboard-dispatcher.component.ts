import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';
import { MatDialog } from '@angular/material/dialog';
import { FormGroup, FormControl } from '@angular/forms';
import { Order } from 'src/app/models/order';


@Component({
  selector: 'app-dashboard-dispatcher',
  templateUrl: 'dashboard-dispatcher.component.html',
  styleUrls: ['dashboard-dispatcher.component.css']

})
export class DashboardDispatcherComponent implements OnInit {

  orderList: Order[] = [];

  constructor(private api: OrderService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.api.getOrders().subscribe((response: Order[]) => {
      this.orderList = response;
    });
  }
  openDialog() {
    const dialogRef = this.dialog.open(DialogContentAssign);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  deleteOrder() {

  }

}

@Component({
  selector: 'dialog-content-example-dialog',
  templateUrl: './dialog-content-assign.html',
})
export class DialogContentAssign {

  constructor(private api: OrderService) { }
  orderForm: FormGroup = new FormGroup({
    trucker: new FormControl(''),
    startDeliver: new FormControl(''),
    endDeliver: new FormControl(''),
  });
  saveOrder() {

  }

}
