import { Component, OnInit,ViewEncapsulation } from '@angular/core';
import { SharedService } from '../shared.service';
import { EmployeeService } from '../employee.service';
import { ProductService } from '../product.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
  encapsulation: ViewEncapsulation.None // Disable Angular Styles

})
export class AdminComponent implements OnInit {
  receivedData: any;
  id: number;
  fname: String;
  lname: String;
  age: number;
  phone: number;
  email: String;
  position: String;
  performanceList: any[] = [];
  topPerformersList: any[] = [];
  employeeCount: number = 0;
  awardCount: number = 0;
  productCount: number = 0;
  activeEmployees: any[] = [];

  constructor(private sharedService: SharedService, private employeeService: EmployeeService, private productService: ProductService, private authService: AuthService) { }

  ngOnInit() {

    const topPerformers = localStorage.getItem('topPerformers');
    const awardCount = localStorage.getItem('awardCount');
    const productCount = localStorage.getItem('productCount');

    if (topPerformers && awardCount && productCount) {
      // Data exists in localStorage, use it
      this.topPerformersList = JSON.parse(topPerformers);
      this.employeeCount = this.topPerformersList.length;
      this.awardCount = +awardCount; // Convert to number
      this.productCount = +productCount; // Convert to number
    }
    this.receivedData = this.sharedService.getData();
    this.fname = this.receivedData.employeeFirstName;;
    this.lname = this.receivedData.employeeLastName;
    this.age = this.receivedData.employeeAge;
    this.phone = this.receivedData.employeePhone;
    this.email = this.receivedData.employeeEmail;
    this.position = this.receivedData.employeePosition;
    this.id = this.receivedData.employeeId;

    this.employeeService.getTopPerformers().subscribe(
      (response) => {
        this.topPerformersList = response;
        this.activeEmployees = this.topPerformersList.filter(employee => employee.employeeStatus === 'active');
        this.employeeCount = this.topPerformersList.length;
        localStorage.setItem('topPerformers', JSON.stringify(this.topPerformersList));
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );

    this.employeeService.getCountOfAwards().subscribe(
      (response) => {
        this.awardCount = response;
        localStorage.setItem('awardCount', this.awardCount.toString());
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );

    this.productService.getCountOfProducts().subscribe(
      (response) => {
        this.productCount = response;
        localStorage.setItem('productCount', this.productCount.toString());
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );

  }

  truncateDate(date: string): string {
    return date.length > 10 ? date.substring(0, 10) : date; //to truncate the date coming from db (in employee Performance) to just 10 elements
  }

  logout() {
    this.authService.logout();
  }

}
