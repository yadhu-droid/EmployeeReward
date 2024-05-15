import { Component, OnInit } from '@angular/core';
import { SharedService } from '../shared.service';
import { EmployeeService } from '../employee.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  receivedData: any;
  employeeData:any;
  id:number;
  fname:String;
  lname:String;
  age:number;
  phone:number;
  email:String;
  position:String;
  totalPoints:number;
  totalDebitPoints:number;
  totalCurrentPoints:number;
  role:String;
  performanceList: any[] = [];
  topPerformersList: any[] = [];


  constructor(private sharedService: SharedService, private employeeService:EmployeeService,private authService:AuthService) {}

  ngOnInit() {

    this.receivedData = this.sharedService.getData();
    this.id=this.receivedData.employeeId;

    this.employeeService.getEmployeeDetails(this.id).subscribe(
      (response) => {
        this.employeeData=response;
        this.fname=this.employeeData.employeeFirstName;
        this.lname=this.employeeData.employeeLastName;
        this.age=this.employeeData.employeeAge;
        this.phone=this.employeeData.employeePhone;
        this.email=this.employeeData.employeeEmail;
        this.position=this.employeeData.employeePosition;
        this.totalPoints=this.employeeData.employeeTotalPoints;
        this.totalDebitPoints=this.employeeData.employeeTotalDebitPoints;
        this.totalCurrentPoints=this.totalPoints-this.totalDebitPoints;
        this.role=this.employeeData.employeeRole;
        this.sharedService.setPoints(this.totalCurrentPoints);
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );

    this.employeeService.getEmployeePerformance(this.id).subscribe(
      (response) => {
        this.performanceList=response;
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );
  }

  truncateDate(date: string): string {
    return date.length > 10 ? date.substring(0, 10) : date; //to truncate the date coming from db (in employee Performance) to just 10 elements
  }

  logout(){
    this.authService.logout();
  }
}
