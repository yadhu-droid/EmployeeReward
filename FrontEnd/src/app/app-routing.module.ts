import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { IndexpageComponent } from './indexpage/indexpage.component';
import { EmployeeComponent } from './employee/employee.component';
import { AdminComponent } from './admin/admin.component';
import { ErrorpageComponent } from './errorpage/errorpage.component';
import { AuthGuardService } from './auth-guard.service';

const routes: Routes = [
  { path: '', component: IndexpageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'employee', component:EmployeeComponent,canActivate:[AuthGuardService]},
  { path: 'admin',component:AdminComponent,canActivate:[AuthGuardService]},
  { path: 'error',component:ErrorpageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
