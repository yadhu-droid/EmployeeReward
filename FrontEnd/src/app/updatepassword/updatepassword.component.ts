import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';
import { SharedService } from '../shared.service';

@Component({
  selector: 'app-updatepassword',
  templateUrl: './updatepassword.component.html',
  styleUrls: ['./updatepassword.component.css']
})
export class UpdatepasswordComponent {

  registerForm:FormGroup;
  showSuccessMessage:number=-1;
  receivedData:any;
  employeeId:number;

  constructor(private authService : AuthService, private fb:FormBuilder, private employeeService:EmployeeService, private router:Router, private sharedService:SharedService){}

  ngOnInit() {
    this.registerForm = this.fb.group({
      oldPassword: ['', Validators.required],
      newPassword: ['', Validators.required]
    });

    this.receivedData = this.sharedService.getData();
    this.employeeId=this.receivedData.employeeId;

  }

  logout(){
    this.authService.logout();
  }

  onSubmit() {
    if (this.registerForm.valid) {
      // Access form values using this.myForm.value
      const formData = this.registerForm.value;
      formData.employeeId = this.employeeId;
      this.employeeService.changePassword(formData)
      .subscribe(
        (response:number) => {
          if(response==1){
            this.showSuccessMessage = response;
            setTimeout(() => {
              this.showSuccessMessage = -1;
            }, 2000);
          }
          if(response==2){
            this.showSuccessMessage=response;
            this.router.navigate(['/employee']);
            setTimeout(() => {
              this.showSuccessMessage = -1;
            }, 2000);
          }
          if(response==0){
            this.showSuccessMessage=response;
            setTimeout(() => {
              this.showSuccessMessage = -1;
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
