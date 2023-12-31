import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatSelectModule } from '@angular/material/select';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';

import { UserManagementComponent } from './user-management/user-management.component';
import { DialogAddUserDialog } from './user-management/user-management.component';
import { HttpInterceptorProvider } from './interceptor/HttpRequest.interceptor';
import { OrderCardComponent } from './dashboard/order-card/order-card.component';
import { TruckerDashboardComponent } from './dashboard/trucker-dashboard/trucker-dashboard.component';
import { AdminDashboardComponent } from './dashboard/admin-dashboard/admin-dashboard.component';



import { DashboardComponent } from './dashboard/dashboard-user/dashboard.component';
import { DashboardDispatcherComponent } from './dashboard/dashboard-dispatcher/dashboard-dispatcher.component';
import { DialogContentExampleDialog } from './dashboard/dashboard-user/dashboard.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { UserProfileComponent } from './dashboard/user-profile/user-profile.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    DashboardDispatcherComponent,
    DialogContentExampleDialog,
    UserManagementComponent,
    DialogAddUserDialog,
    OrderCardComponent,
    TruckerDashboardComponent,
    AdminDashboardComponent,
    UserProfileComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    FormsModule,
    HttpClientModule,
    MatListModule,
    MatIconModule,
    MatDialogModule,
    MatSelectModule,
    MatPaginatorModule,
    MatDialogModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTableModule,
    MatSnackBarModule,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule,
    MatDividerModule,
    MatListModule,
  ],
  providers: [HttpInterceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
