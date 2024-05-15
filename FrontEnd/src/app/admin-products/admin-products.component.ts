import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { SharedService } from '../shared.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-admin-products',
  templateUrl: './admin-products.component.html',
  styleUrls: ['./admin-products.component.css']
})
export class AdminProductsComponent implements OnInit {
  showSuccessMessage: boolean = false;
  productList: any[] = []; //for ngonit
  imageArray: any[] = []; //for storing image data thats coming in arrays
  receivedData: any;
  employeeId: number;
  totalPoints: number;
  totalDebitPoints: number;
  currentMerchName: String;
  currentMerchCost: number;
  currentMerchId: number;
  showDiv: Boolean = false;
  chunkedProducts: any[][] = []; //to group the orders into 5 rows each,we are going to insert 5 elements 5 elements to this.

  constructor(private productService: ProductService, private sharedService: SharedService, private router: Router, private authService: AuthService) { }

  ngOnInit() {
    this.productService.getProductList().subscribe(
      (response) => {
        this.productList = response;
        this.chunkProducts();
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );

    this.receivedData = this.sharedService.getData();
    this.totalPoints = this.receivedData.employeeTotalPoints;
    this.totalDebitPoints = this.receivedData.employeeTotalDebitPoints;
  }

  //logic for grouping 5 elements each in a row
  private chunkProducts() {
    const chunkSize = 4;
    for (let i = 0; i < this.productList.length; i += chunkSize) {
      this.chunkedProducts.push(this.productList.slice(i, i + chunkSize));
    }
  }

  deleteProduct(merchId: number) {
    this.productService.deleteProduct(merchId).subscribe(
      (response) => {
        this.showSuccessMessage = true;
        setTimeout(() => {

          const currentUrl = this.router.url;
          this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
            this.router.navigate([currentUrl]);
          });

          this.showSuccessMessage = false;
        }, 2000);
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );
  }

  logout() {
    this.authService.logout();
  }

}
