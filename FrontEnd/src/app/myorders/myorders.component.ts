import { Component, OnInit } from '@angular/core';
import { SharedService } from '../shared.service';
import { EmployeeService } from '../employee.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-myorders',
  templateUrl: './myorders.component.html',
  styleUrls: ['./myorders.component.css']
})
export class MyordersComponent implements OnInit {
  receivedData:any;
  employeeId:number;
  myOrderList: any[] = [];
  chunkedOrders: any[][] = []; //to group the orders into 5 rows each,we are going to insert 5 elements 5 elements to this.

  constructor(private sharedService:SharedService, private employeeService:EmployeeService, private authService:AuthService) {}
  ngOnInit(): void {
    this.receivedData = this.sharedService.getData();
    this.employeeId=this.receivedData.employeeId;

    this.employeeService.getMyOrders(this.employeeId).subscribe(
      (response) => {
        this.myOrderList=response;
        this.chunkOrders();
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );

  }

  //logic for grouping 5 elements each in a row
  private chunkOrders() {
    const chunkSize = 4;
    for (let i = 0; i < this.myOrderList.length; i += chunkSize) {
      this.chunkedOrders.push(this.myOrderList.slice(i, i + chunkSize));
    }
  }

  logout(){
    this.authService.logout();
  }
  
}
