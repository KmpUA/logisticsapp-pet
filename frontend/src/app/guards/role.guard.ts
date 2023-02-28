import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  constructor(private authService: AuthService) { }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const roles: string[] = route.data.expetedRoles;
    const role: string = this.authService.user.role!;
    console.log(role, roles)
    if (!roles)
      return false;
    const roleMatches = roles.findIndex((_role) => role == _role);
    console.log(roleMatches)
    if (roleMatches != -1)
      return true
    return false;
  }

}
