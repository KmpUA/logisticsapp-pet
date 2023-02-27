import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardDispatcherComponent } from './dashboard/dashboard-dispatcher/dashboard-dispatcher.component';
import { DashboardComponent } from './dashboard/dashboard-user/dashboard.component';
import { UserProfileComponent } from './dashboard/user-profile/user-profile.component';
import { TruckerDashboardComponent } from './dashboard/trucker-dashboard/trucker-dashboard.component';
import { LoginComponent } from './login/login.component';
import { UserManagementComponent } from './user-management/user-management.component';

const routes: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'dispatcher', component: DashboardDispatcherComponent },
  { path: 'login', component: LoginComponent },
  { path: 'user-management', component: UserManagementComponent },
  { path: 'user-profile', component: UserProfileComponent },
  { path: 'dashboard-trucker', component: TruckerDashboardComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
