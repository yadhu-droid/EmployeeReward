import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class UpdateService {

  constructor(private http: HttpClient) { }
  private apiUrl = environment.apiUrl;

  updatePassword(employeeData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/updatePassword`, employeeData);
  }
}
