import { Injectable } from '@angular/core';
import { SharedService } from './shared.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  loggedIn: boolean = false;

  constructor(private sharedService: SharedService) {
    this.loggedIn = this.sharedService.getData() ? true : false;
  }

  login(){
    this.loggedIn=true;
  }  //this is called on login

  logout(){
    this.loggedIn=false;
  } //this is called on logout

  isAuthenticated(){
    return this.loggedIn;
  } //this returns the current value for AuthGuard to check 
}
