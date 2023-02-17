import { Component, Inject, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatTable } from '@angular/material/table';
import { User } from '../models/User';
import { UsersService } from '../services/users.service';
import { FormControl, FormGroup } from '@angular/forms';
import { Statuses } from '../models/Statueses';
import { Roles } from '../models/Roles';


@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent {
  @ViewChild(MatTable) table!: MatTable<any>;

  users: User[] = [];
  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'phone', 'role', 'status'];
  roles = Object.values(Roles);
  statuses = Object.values(Statuses);

  constructor(private userService: UsersService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.userService.getUsers().subscribe(
      response => {
        console.log(response);
        this.users = response;

      }
    );
  }

  changeRole(role: string, element: User) {
    console.log(role, element);
    this.userService.updateRole(element.id!, role).subscribe(
      response => {
        console.log(response);
      }
    )
  }

  changeStatus(status: string, element: User) {
    console.log(status, element);
    this.userService.updateStatus(element.id!, status).subscribe(
      response => {
        console.log(response);
      }
    )
  }

  changePage(event: PageEvent) {
    let page: number = event.pageIndex;
    console.log(page);
    page++;
    this.userService.getUsers(page).subscribe(
      response => {
        console.log(response);
        this.users.pop(); // replace this with: this.users = response;
        this.table.renderRows();
      }
    );
  }

  openDialog() {
    this.dialog.open(DialogAddUserDialog)
  }
}


@Component({
  selector: 'dialog-data-example-dialog',
  templateUrl: './dialog-add-user-dialog.html',
  styleUrls: ['./user-management.component.css']

})
export class DialogAddUserDialog {
  roles = Object.values(Roles);

  userForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl(''),
    phone: new FormControl(''),
    role: new FormControl(''),
    password: new FormControl(''),
  });

  constructor(private userService: UsersService) { }

  onSubmit(userForm: FormGroup) {
    if (userForm.valid) {
      this.userService.addUser(userForm.value).subscribe(
        response => console.log(response)
      )
    }
  }
}