import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TruckerDashboardComponent } from './dashboard/trucker-dashboard/trucker-dashboard.component';
import { LoginComponent } from './login/login.component';
import { UserManagementComponent } from './user-management/user-management.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'user-management', component: UserManagementComponent },
  { path: 'dashboard-trucker', component: TruckerDashboardComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
