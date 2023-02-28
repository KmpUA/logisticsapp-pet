import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardDispatcherComponent } from './dashboard/dashboard-dispatcher/dashboard-dispatcher.component';
import { DashboardComponent } from './dashboard/dashboard-user/dashboard.component';
import { UserProfileComponent } from './dashboard/user-profile/user-profile.component';
import { AdminDashboardComponent } from './dashboard/admin-dashboard/admin-dashboard.component';
import { TruckerDashboardComponent } from './dashboard/trucker-dashboard/trucker-dashboard.component';
import { LoginComponent } from './login/login.component';
import { UserManagementComponent } from './user-management/user-management.component';
import { RoleGuard } from './guards/role.guard';

const routes: Routes = [
  {
    path: 'customer',
    component: DashboardComponent,
    canActivate: [RoleGuard],
    data: { expetedRoles: ['CUSTOMER'] }
  },
  {
    path: 'dispatcher',
    component: DashboardDispatcherComponent,
    canActivate: [RoleGuard],
    data: { expetedRoles: ['DISPATCHER'] }
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'user-management',
    component: UserManagementComponent,
    canActivate: [RoleGuard],
    data: { expetedRoles: ['ADMIN'] }
  },
  {
    path: 'user-profile',
    component: UserProfileComponent,
    canActivate: [RoleGuard]
  },
  {
    path: 'dashboard-trucker',
    component: TruckerDashboardComponent,
    canActivate: [RoleGuard],
    data: { expetedRoles: ['TRUCKER'] }
  },
  {
    path: 'dashboard-admin',
    component: AdminDashboardComponent,
    canActivate: [RoleGuard],
    data: { expetedRoles: ['ADMIN'] }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
