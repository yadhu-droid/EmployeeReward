import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ProductService } from '../product.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-productsadd',
  templateUrl: './productsadd.component.html',
  styleUrls: ['./productsadd.component.css']
})
export class ProductsaddComponent implements OnInit {
  showSuccessMessage: boolean = false;
  productForm:FormGroup;

    constructor(private fb: FormBuilder, private productService:ProductService, private router: Router, private authService:AuthService) { }

    ngOnInit() {
      this.productForm = this.fb.group({
        merchName: ['', Validators.required],
        merchCategory: ['', Validators.required],
        merchDescription: [null, Validators.required,],
        merchManufacturer: [null, Validators.required],
        merchQuantity: ['', Validators.required],
        merchPoints: [null, Validators.required],
        base64Image: ['', [Validators.required]],
        merchRedemptionConditions: ['', Validators.required],
        merchTermsAndConditions: ['', Validators.required],
        merchExpiryDate: [''],
        merchStatus:['In Stock'],
      });
    }

    onFileSelected(event: any): void {
      const file: File = event.target.files[0];
      const reader = new FileReader();
  
        reader.onloadend = () => {
          const base64Image = reader.result as string;
          console.log(base64Image);
          this.productForm.patchValue({
            base64Image
          });
        };
        if (file) {
    
          reader.readAsDataURL(file);
        }
    }

    onSubmit() {
      if (this.productForm.valid) {
        // Access form values using this.myForm.value
        const formData = this.productForm.value;
        console.log(formData)
        this.productService.createProduct(formData)
        .subscribe(
          (response:any) => {
            this.showSuccessMessage = true;
        setTimeout(() => {
          this.showSuccessMessage = false;
          this.router.navigate(['/adminproducts']); //placed it here after timeout for DOM to render deleted message
        }, 2000);
          },
          (error:any) => {
            alert('Error creating Product');
            console.error('Error creating Product', error);
            // Handle error (e.g., show an error message)
          }
        );
    } else {
      console.log('Form is invalid');
    }
  }

  logout(){
    this.authService.logout();
  }

}
