import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = environment.apiUrl;  // Replace with your Spring Boot backend URL

  constructor(private http: HttpClient) { }

  createProduct(productData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/product/add`, productData);
  }

  getProductList(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/product/getproducts`);
  }

  buyProduct(merchendiseId:number, employeeId:number, merchendiseCost:number): Observable<any> {
    const dataToSend = {merchendiseId,employeeId,merchendiseCost};
    return this.http.post(`${this.apiUrl}/product/submitorder`, dataToSend);
  }

  getCountOfProducts():Observable<number>{
    return this.http.get<number>(`${this.apiUrl}/product/getproductcount`);
  }

  deleteProduct(merchId:number):Observable<any>{
    return this.http.delete(`${this.apiUrl}/product/delete/${merchId}`);
  }

  addToCartProduct(merchendiseId:number, employeeId:number,merchendiseCost:number): Observable<any> {
    const dataToSend = {merchendiseId,employeeId,merchendiseCost};
    return this.http.post(`${this.apiUrl}/product/addtocart`, dataToSend);
  }

  getMyCart(employeeId:number):Observable<any>{
    return this.http.get(`${this.apiUrl}/product/getmycart/${employeeId}`);
  }

  deleteCartOrder(orderId:number):Observable<any>{
    return this.http.delete(`${this.apiUrl}/product/deletecartid/${orderId}`);
  }

  buyBulkProduct(products:any): Observable<any[]> {
    return this.http.post<any[]>(`${this.apiUrl}/product/bulkproducts`,products);
  }

}
