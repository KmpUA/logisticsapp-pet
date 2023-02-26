import { Component, Input } from '@angular/core';
import { Order } from 'src/app/models/order';
import { Roles } from 'src/app/models/roles';

@Component({
  selector: 'order-card',
  templateUrl: './order-card.component.html',
  styleUrls: ['./order-card.component.css']
})
export class OrderCardComponent {
  @Input() role: Roles | null = null;
  @Input() item: Order = new Order();
  @Input() isTrucker: boolean = false;
}
