import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-viewemployees',
  templateUrl: './viewemployees.component.html',
  styleUrls: ['./viewemployees.component.css'],
})
export class ViewemployeesComponent {


  showSuccessMessage: boolean = false;
  employeeList: any[] = [];
  activeEmployees:any[] = [];
  inactiveEmployees:any[] = [];
  chunkedEmployees: any[][] = []; //to group the orders into 5 rows each,we are going to insert 5 elements 5 elements to this.
  inactivechunkedEmployees: any[][] = [];
  searchTerm: string = '';

  

  

  constructor(private authService:AuthService, private employeeService:EmployeeService, private router:Router) {  }

  ngOnInit(): void {

    this.employeeService.getEmployeeList().subscribe(
      (response) => {
        this.employeeList=response;
        this.activeEmployees = this.employeeList.filter(employee => employee.employeeStatus === 'active');
        this.inactiveEmployees = this.employeeList.filter(employee => employee.employeeStatus === 'inactive');
        this.chunkEmployees();
        this.chunkInactiveEmployees();
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );

  }

  //logic for grouping 5 elements each in a row
  private chunkEmployees() {
    const chunkSize = 4;
    for (let i = 0; i < this.activeEmployees.length; i += chunkSize) {
      this.chunkedEmployees.push(this.activeEmployees.slice(i, i + chunkSize));
    }
  }

  private chunkInactiveEmployees(){
    const chunkSize = 4;
    for (let i = 0; i < this.inactiveEmployees.length; i += chunkSize) {
      this.inactivechunkedEmployees.push(this.inactiveEmployees.slice(i, i + chunkSize));
    }
  }

  deleteEmployee(employeeId:number){
    this.employeeService.deleteEmployee(employeeId).subscribe(
      (response) => {
        this.showSuccessMessage = true;
        setTimeout(() => {
          this.showSuccessMessage = false;

          const currentUrl = this.router.url;
          this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          this.router.navigate([currentUrl]);
          });

        }, 1500);
      },
      (error) => {
        console.error('Error Deleting Employee:', error);
      }
    );
  }

  logout(){
    this.authService.logout();
  }

}
