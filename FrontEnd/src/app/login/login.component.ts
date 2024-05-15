import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { SharedService } from '../shared.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  constructor(private fb: FormBuilder, private loginService: LoginService, private router: Router, private sharedService: SharedService, private authService: AuthService) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      // Access form values using this.myForm.value
      const formData = this.loginForm.value;
      this.loginService.validateEmployee(formData)
        .subscribe(
          (response: any) => {
            if (response !== null) {
              this.authService.login();
              this.sharedService.setData(response);
              if (response.employeeRole === 'Employee') {
                this.router.navigate(['/employee']);
              }
              if (response.employeeRole === 'Admin') {
                this.router.navigate(['/admin']);
              }
            }
            else {
              alert("Sorry. Password Mismatch")
            }
            // Handle success (e.g., show a success message)
          },
          (error: any) => {
            this.router.navigate(['/error']);
          }
        );
    } else {
      console.log('Form is invalid');
    }
  }
}
