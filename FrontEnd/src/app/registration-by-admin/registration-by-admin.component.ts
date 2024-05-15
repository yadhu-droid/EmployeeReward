import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-registration-by-admin',
  templateUrl: './registration-by-admin.component.html',
  styleUrls: ['./registration-by-admin.component.css']
})
export class RegistrationByAdminComponent implements OnInit {
  showSuccessMessage: number = -1;
  registerForm:FormGroup;

  constructor(private fb: FormBuilder, private authService:AuthService, private employeeService: EmployeeService, private router: Router) { }

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
  }

  onSubmit() {
    if (this.registerForm.valid) {
      // Access form values using this.myForm.value
      const formData = this.registerForm.value;
      this.employeeService.createEmployee(formData)
      .subscribe(
        (response:any) => {
          if(response==1){
            this.showSuccessMessage = response;
            setTimeout(() => {
              this.showSuccessMessage = -1;
              this.router.navigate(['/viewemployees']);
            }, 2000);
          }
          if(response==2){
            this.showSuccessMessage=response;
            setTimeout(() => {
              this.showSuccessMessage = -1;
              this.router.navigate(['/employeebyadmin']);
            }, 2000);
          }
          if(response==0){
            this.showSuccessMessage=response;
            setTimeout(() => {
              this.showSuccessMessage = -1;
              this.router.navigate(['/employeebyadmin']);
            }, 2000);
          }
        },
        (error:any) => {
          alert('Error creating Employee');
          console.error('Error creating employee', error);
          // Handle error (e.g., show an error message)
        }
      );
  } else {
    console.log('Form is invalid');
  }
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

logout(){
  this.authService.logout();
}

}
