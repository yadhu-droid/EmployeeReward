import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IndexpageComponent } from './indexpage/indexpage.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
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
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CartComponent } from './cart/cart.component';

@NgModule({
  declarations: [
    AppComponent,
    IndexpageComponent,
    LoginComponent,
    RegistrationComponent,
    EmployeeComponent,
    AdminComponent,
    ErrorpageComponent,
    ProductsComponent,
    ProductsaddComponent,
    PointsaddComponent,
    RegistrationByAdminComponent,
    MyordersComponent,
    AdminProductsComponent,
    ViewemployeesComponent,
    UpdatepasswordComponent,
    CartComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
  ],
  providers: [AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { 
}
