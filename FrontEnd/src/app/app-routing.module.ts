import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { IndexpageComponent } from './indexpage/indexpage.component';
import { EmployeeComponent } from './employee/employee.component';
import { AdminComponent } from './admin/admin.component';
import { ErrorpageComponent } from './errorpage/errorpage.component';
import { ProductsComponent } from './products/products.component';
import { ProductsaddComponent } from './productsadd/productsadd.component';
import { PointsaddComponent } from './pointsadd/pointsadd.component';
import { RegistrationByAdminComponent } from './registration-by-admin/registration-by-admin.component';
import { MyordersComponent } from './myorders/myorders.component';
import { AdminProductsComponent } from './admin-products/admin-products.component';
import { AuthGuardService } from './auth-guard.service';
import { ViewemployeesComponent } from './viewemployees/viewemployees.component';
import { UpdatepasswordComponent } from './updatepassword/updatepassword.component';
import { CartComponent } from './cart/cart.component';

const routes: Routes = [
  { path: '', component: IndexpageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'employee', component:EmployeeComponent,canActivate:[AuthGuardService]},
  { path: 'admin',component:AdminComponent,canActivate:[AuthGuardService]},
  { path: 'error',component:ErrorpageComponent},
  { path: 'products',component:ProductsComponent,canActivate:[AuthGuardService]},
  { path: 'addproducts',component:ProductsaddComponent,canActivate:[AuthGuardService]},
  { path: 'addpoints', component:PointsaddComponent,canActivate:[AuthGuardService]},
  { path: 'employeebyadmin', component:RegistrationByAdminComponent,canActivate:[AuthGuardService]},
  { path: 'orders', component:MyordersComponent,canActivate:[AuthGuardService]},
  { path: 'adminproducts',component:AdminProductsComponent,canActivate:[AuthGuardService]},
  { path: 'viewemployees',component:ViewemployeesComponent,canActivate:[AuthGuardService]},
  { path: 'updatepassword',component:UpdatepasswordComponent,canActivate:[AuthGuardService]},
  { path: 'cart',component:CartComponent,canActivate:[AuthGuardService]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
