import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Order } from 'src/app/models/order';
import { Roles } from 'src/app/models/roles';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'order-card',
  templateUrl: './order-card.component.html',
  styleUrls: ['./order-card.component.css']
})
export class OrderCardComponent {
  @Input() role: Roles | null = null;
  @Input() item: Order | any = new Order();
  @Input() isAdmin: boolean = false;
  @Input() isTrucker: boolean = false;
  @Output() orderCompleted = new EventEmitter<Order>();
  @Output() orderDelete = new EventEmitter<number>();
  constructor(private auth: AuthService) {
  }
  onCompleteToggle() {
    this.item.trucker = this.auth.user.id;
    this.item.customer = this.item.customer.id;
    this.item.completed = !this.item.completed;
    this.orderCompleted.emit(this.item);
  }

  onDelete() {
    this.orderDelete.emit(this.item.id);
  }
}
