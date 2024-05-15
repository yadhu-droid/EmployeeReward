import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
    registerForm:FormGroup;
    showSuccessMessage:number=-1;

    constructor(private fb: FormBuilder, private employeeService: EmployeeService, private router: Router) { }

    ngOnInit() {
      this.registerForm = this.fb.group({
        fname: ['', Validators.required],
        lname: ['', Validators.required],
        email: [null, [Validators.required,Validators.email,this.doubleComValidator()]],
        phone: [null, [Validators.required,this.phoneValidator]],
        age: ['', Validators.required],
        designation: [null, Validators.required],
        password: ['', Validators.required],
        gender:['', Validators.required]
      });

      this.registerForm.addControl('confirmPassword', this.fb.control('', Validators.required)); //since its not a field of for group, its done like this
      this.registerForm.get('confirmPassword')?.setValidators(this.passwordMatchValidator.bind(this));
    }

    phoneValidator(control: AbstractControl): { [key: string]: any } | null {
      const phonePattern = /^\d{10}$/;
      if (control.value && !phonePattern.test(control.value)) {
        return { 'invalidPhone': true };
      }
      return null;
    }

    doubleComValidator(): ValidatorFn {
      return (control: AbstractControl): { [key: string]: any } | null => {
        const email = control.value;
        
        if (!email) {
          // Return null if the value is null or undefined
          return null;
        }
        
        // Regular expression with a lookahead assertion
        const pattern = /^.*@.*\..*$/i;
        
        if (pattern.test(email)) {
          // Valid Gmail address with no characters after '.com'
          return null;
        } else {
          // Invalid Gmail address with characters after '.com'
          return { 'invalidDoubleCom': true };
        }
      };
    }

    passwordMatchValidator(control: AbstractControl) {
      const password = this.registerForm.get('password')?.value;
      const confirmPassword = control.value;
  
      if (password !== confirmPassword) {
        return { passwordMismatch: true };
      } else {
        return null;
      }
    }

    onSubmit() {
      if (this.registerForm.valid) {
        // Access form values using this.myForm.value
        const formData = this.registerForm.value;
        this.employeeService.createEmployee(formData)
        .subscribe(
          (response:number) => {
            if(response==1){
              this.showSuccessMessage = response;
              setTimeout(() => {
                this.showSuccessMessage = -1;
                this.router.navigate(['/login']);
              }, 2000);
            }
            if(response==2){
              this.showSuccessMessage=response;
              setTimeout(() => {
                this.showSuccessMessage = -1;
                this.router.navigate(['/login']);
              }, 2000);
            }
            if(response==0){
              this.showSuccessMessage=response;
              setTimeout(() => {
                this.showSuccessMessage = -1;
                this.router.navigate(['/register']);
              }, 2000);
            }

            // Handle success (e.g., show a success message)
          },
          (error:any) => {
            alert('Error creating Employee');
            // Handle error (e.g., show an error message)
          }
        );
    } else {
      console.log('Form is invalid');
    }
  }
}
