import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private apiUrl = environment.apiUrl;  // Replace with your Spring Boot backend URL

  constructor(private http: HttpClient) { }

  createEmployee(employeeData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/employee/add`, employeeData);
  }

  changePassword(employeeData:any): Observable<any> {
    return this.http.post(`${this.apiUrl}/employee/changepassword`, employeeData);
  }

  getEmployeeList(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/employee/getemployees`);
  }

  addTransactionCredit(pointData: any): Observable<any[]> {
    return this.http.post<any[]>(`${this.apiUrl}/employee/addpoints`,pointData);
  }

  getEmployeePerformance(employeeId:number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/employee/getperformance/${employeeId}`);
  }

  getEmployeeDetails(employeeId:number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/employee/getdetails/${employeeId}`);
  }

  getTopPerformers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/employee/getbestemployees`);
  }

  getMyOrders(employeeId:number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/employee/getmyorders/${employeeId}`);
  }

  getCountOfAwards(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/employee/getawardcount`);
  }

  deleteEmployee(employeeId:number){
    return this.http.delete(`${this.apiUrl}/employee/delete/${employeeId}`);
  }

}
