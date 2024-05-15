import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiUrl = environment.apiUrl; // Replace with your Spring Boot backend URL

  constructor(private http: HttpClient) { }

  validateEmployee(employeeData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/employee/login`, employeeData);
  }
}
