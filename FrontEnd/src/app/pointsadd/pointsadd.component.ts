import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pointsadd',
  templateUrl: './pointsadd.component.html',
  styleUrls: ['./pointsadd.component.css']
})
export class PointsaddComponent implements OnInit {
  showSuccessMessage: boolean = false;

  employeeList: any[] = []; //for ngonit

  constructor(private employeeService: EmployeeService, private authService:AuthService,private router:Router) {}
  

  ngOnInit() {
    this.employeeService.getEmployeeList().subscribe(
      (response) => {
        this.employeeList = response.map((emp) => { //mapping each employee
          return {
            ...emp, //spread existing employee object
            point: 0, //adding these new fields with value to each object
            award: '',
            remarks: ''
          }
        }); // Assuming your service returns an array of data
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );
  }

  onSubmit() {
    const updatedEmployees = this.employeeList
      .filter((emp) => Number(emp.point) > 0)
      .map((emp) => {
        return {
          employeeId: emp.employeeId,
          numberofPoints: emp.point,
          award: emp.award,
          remarks: emp.remarks
        }
      });
      this.employeeService.addTransactionCredit(updatedEmployees).subscribe(
        (response) => {
          this.showSuccessMessage = true;
        setTimeout(() => {
          this.showSuccessMessage = false;
          this.router.navigate(['/admin']);
        }, 1000);
        },
        (error) => {
          alert("error")
        }
      ); 
  }
  
  logout(){
    this.authService.logout();
  }
}
