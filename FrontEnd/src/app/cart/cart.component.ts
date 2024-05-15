import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { ProductService } from '../product.service';
import { SharedService } from '../shared.service';
import { Router } from '@angular/router';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  receivedData:any;
  employeeData:any;
  employeeId:number;
  myCart: any[] = [];
  activeCart:any[] = [];
  cartToSend:any[] = [];
  cartAmountSum:number=0;
  totalCurrentPoints:number;
  totalPoints:number;
  totalDebitPoints:number;
  showTotal:boolean=false;
  showSuccessMessage:boolean=false;
  showFailedMessage:boolean=false;
  showSuccessMessage2:boolean=false;

  constructor(private authService:AuthService, private productService:ProductService,private sharedService:SharedService, private router:Router, private employeeService:EmployeeService){}

  ngOnInit(): void {
    this.receivedData = this.sharedService.getData();
    this.employeeId=this.receivedData.employeeId;

    this.productService.getMyCart(this.employeeId).subscribe(
      (response) => {
        this.myCart=response;
        this.activeCartList();
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );

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

  }

  activeCartList(){
    this.activeCart = this.myCart.filter(item => item.active === true);
    if(this.activeCart.length!=0){
      this.showTotal=true;
    }
    this.activeCart.forEach(cartItem => {
      if (cartItem.product && cartItem.product.merchCost) {
          this.cartAmountSum += cartItem.product.merchCost;
      }
  });

  }

  deleteOrder(orderId:number){
    this.productService.deleteCartOrder(orderId).subscribe(
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
        
      }
    );
  }

  buyBulkProduct(){

    if(this.cartAmountSum<=this.totalCurrentPoints){
      // Iterate over each element in the original array
      this.activeCart.forEach(item => {
      // Extract required attributes
      const { employee, product } = item;
      const { employeeId } = employee;
      const { merchId : merchendiseId } = product;
      const { merchCost : merchendiseCost } = product;
  
      // Add selected attributes to the new array
      this.cartToSend.push({ employeeId, merchendiseCost, merchendiseId });
      });

      this.productService.buyBulkProduct(this.cartToSend).subscribe(
        (response) => {
          this.showSuccessMessage2 = true;
        setTimeout(() => {
          this.showSuccessMessage2 = false;

          const currentUrl = this.router.url;
          this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          this.router.navigate([currentUrl]);
          });

        }, 1500);
        },
        (error) => {
          alert("error")
        }
      ); 

    }
    else{
      this.showFailedMessage = true;
      setTimeout(() => {
        this.showFailedMessage = false;
      }, 1500);
    }
  }

  logout() {
    this.authService.logout();
  }

}
