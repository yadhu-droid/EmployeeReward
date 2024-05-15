import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
  currentPoints:number;

  setData(data: any) {
    localStorage.setItem('employee', JSON.stringify(data));
  }

  getData() {
    let data = localStorage.getItem('employee');
    if(data !== null) {
     data = JSON.parse(data); 
    }
    return data;
  }

  setPoints(currentPoints:any) {
    this.currentPoints=currentPoints;
  }

  getPoints(){
    return this.currentPoints;
  }

}
