import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(private authService : AuthService, private router : Router) { }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    if(this.authService.isAuthenticated()){
      return true;
    }
    else{
      this.router.navigate(['/login']); //if canactivate is false on routing, it navigates to here
      return false;
    }
  }
}
