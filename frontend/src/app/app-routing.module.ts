import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardDispatcherComponent } from './dashboard/dashboard-dispatcher/dashboard-dispatcher.component';
import { DashboardTruckerComponent } from './dashboard/dashboard-trucker/dashboard-trucker.component';
import { DashboardComponent } from './dashboard/dashboard-user/dashboard.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'dispatcher', component: DashboardDispatcherComponent },
  { path: 'trucker', component: DashboardTruckerComponent },
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
