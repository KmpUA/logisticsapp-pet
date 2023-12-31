import { Component, Inject, ViewChild } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTable } from '@angular/material/table';
import { User } from '../models/user';
import { UsersService } from '../services/users.service';
import { FormControl, FormGroup } from '@angular/forms';
import { Statuses } from '../models/statuses';
import { Roles } from '../models/roles';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent {
  @ViewChild(MatTable) table!: MatTable<any>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  users: User[] = [];
  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'phone', 'role', 'status', 'action'];
  roles = Object.values(Roles);
  statuses = Object.values(Statuses);
  length: number = 0;
  constructor(private userService: UsersService, public dialog: MatDialog, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.userService.getUsers(0).subscribe(
      response => {
        this.users = response.content;
        this.length = response.totalElements;
        this.table.renderRows();
      }
    );
  }

  changeRole(role: string, user: User) {
    user.role = role;
    this.userService.updateUser(user).subscribe({
      error: error => {
        this.snackBar.open('Cannot edit user\'s role! Code: ' + error.status, '', {
          duration: 3000
        });
      }
    });
  }

  changeStatus(status: string, user: User) {
    user.status = status;
    this.userService.updateUser(user).subscribe({
      error: error => {
        this.snackBar.open('Cannot edit user\'s status! Code: ' + error.status, '', {
          duration: 3000
        });
      }
    });
  }

  changePage(event: PageEvent) {
    let page: number = event.pageIndex;
    this.userService.getUsers(page).subscribe(
      response => {
        this.users = response.content;
        this.table.renderRows();
      }
    );
  }

  openDialog() {
    const dialogRef = this.dialog.open(DialogAddUserDialog);
    dialogRef.afterClosed().subscribe(result => {
      window.location.reload();
    });
  }

  deleteUser(id: number) {
    this.userService.deleteUser(id).subscribe({
      next: response => {
        this.reloadTable(this.paginator.pageIndex);
      }
    });
  }

  reloadTable(page: number) {
    this.userService.getUsers(page).subscribe(
      response => {
        this.users = response.content;
        this.length = response.totalElements;
        this.paginator.pageIndex = page;
        this.table.renderRows();
      }
    );
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

  constructor(private userService: UsersService, private snackBar: MatSnackBar, private dialogRef: MatDialogRef<DialogAddUserDialog>) { }


  onSubmit(userForm: FormGroup) {
    if (userForm.valid) {
      this.userService.addUser(userForm.value).subscribe({
        error: error => {
          this.snackBar.open('Error while addin user! Code: ' + error.status, '', {
            duration: 3000
          });
        }
      });
    } else {
      this.snackBar.open('Cannot add user! Invalid form', '', {
        duration: 3000
      });
    }
  }
}