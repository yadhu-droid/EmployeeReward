import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { SharedService } from '../shared.service';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  showSuccessMessage: boolean = false;
  showSuccessMessage2: boolean = false;
  employeeData:any;
  productList: any[] = []; //for ngonit
  imageArray : any[] = []; //for storing image data thats coming in arrays
  receivedData: any;
  employeeId:number;
  totalPoints:number;
  totalDebitPoints:number;
  totalCurrentPoints:number;
  currentMerchName:String;
  currentMerchCost:number;
  currentMerchId:number;
  chunkedProducts: any[][] = []; //to group the orders into 5 rows each,we are going to insert 5 elements 5 elements to this.

  constructor(private employeeService:EmployeeService,private productService: ProductService,private sharedService:SharedService, private authService:AuthService,private router:Router) {}

  ngOnInit() {
    this.receivedData = this.sharedService.getData();
    this.employeeId=this.receivedData.employeeId;

    this.employeeService.getEmployeeDetails(this.employeeId).subscribe(
      (response) => {
        this.employeeData=response;
        this.totalPoints=this.employeeData.employeeTotalPoints;
        this.totalDebitPoints=this.employeeData.employeeTotalDebitPoints;
        this.totalCurrentPoints=this.totalPoints-this.totalDebitPoints;
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );

    this.productService.getProductList().subscribe(
      (response) => {
        this.productList=response;
        this.chunkProducts();
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );

  }

  handleSubmit(currentMerchId:number,currentMerchCost:number){
    this.currentMerchId=currentMerchId;
    this.currentMerchCost=currentMerchCost;
    this.productService.buyProduct(this.currentMerchId,this.employeeId,this.currentMerchCost).subscribe(
      (response) => {
        this.showSuccessMessage = true;
        setTimeout(() => {
          this.showSuccessMessage = false;
          this.router.navigate(['/orders']);
        }, 1000);
      },
      (error) => {
        console.error('Error sending data:', error);
      }
    );
  }

  handleCartSubmit(currentMerchId:number,currentMerchCost:number){
    this.receivedData = this.sharedService.getData();
    this.employeeId=this.receivedData.employeeId;
    this.productService.addToCartProduct(currentMerchId,this.employeeId,currentMerchCost).subscribe(
      (response) => {
        this.showSuccessMessage2 = true;
        setTimeout(() => {
          this.showSuccessMessage2 = false;
          this.router.navigate(['/cart']);
        }, 1000);
      },
      (error) => {
        console.error('Error sending data:', error);
      }
    );
  }

   //logic for grouping 5 elements each in a row
   private chunkProducts() {
    const chunkSize = 4;
    for (let i = 0; i < this.productList.length; i += chunkSize) {
      this.chunkedProducts.push(this.productList.slice(i, i + chunkSize));
    }
  }

  logout(){
    this.authService.logout();
  }

}
