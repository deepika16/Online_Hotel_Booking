
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserLogin } from './user/user-login/user-login.component';
import { LoginLayoutComponent } from './layouts/login-layout/login-layout.component';
import { PageLayoutComponent } from './layouts/page-layout/page-layout.component';
import { UserDashboardComponent } from './user/user-dashboard/user-dashboard.component';
import { HomePageComponent } from './frontend/home-page/home-page.component';
import { AboutPageComponent } from './frontend/about-page/about-page.component';
import { ContactPageComponent } from './frontend/contact-page/contact-page.component';
import { LocationComponent } from './frontend/location/location.component';
import { CenterListingComponent } from './frontend/center-listing/center-listing.component';
import { CenterDetailsComponent } from './frontend/center-details/center-details.component';
import { LocationListComponent } from './location/location-list/location-list.component';
import { CreateLocationComponent } from './location/location-add/create-location.component';
import { CreateCenterComponent } from './center/center-add/create-center.component';
import { CenterListComponent } from './center/center-list/center-list.component';
import { LoginComponent } from './frontend/login/login.component';
import { CustomerComponent } from './frontend/customer/customer.component';
import { DashboardComponent } from './frontend/dashboard/dashboard.component';
import { CommentsComponent } from './frontend/comments/comments.component';
import { ContactsListComponent } from './contacts/contacts-list/contacts-list.component';
import { FeedbacksListComponent } from './contacts/feedbacks-list/feedbacks-list.component';
import { FeedbackPageComponent } from './frontend/feedback-page/feedback-page.component';
import { CompanyComponent } from './frontend/company/company.component';
import { CreateCompanyComponent } from './company/company-add/create-company.component';
import { CompanyListComponent } from './company/company-list/company-list.component';
import { CustomerListComponent } from './customer/customer-list/customer-list.component';
import { CreateCustomerComponent } from './customer/customer-add/create-customer.component';
import { PaymentComponent } from './frontend/payment/payment.component';
import { OrderReportComponent } from './frontend/order-report/order-report.component';
import { CenterCartComponent } from './frontend/center-cart/center-cart.component';
import { OrdersListComponent } from './orders/orders-list/orders-list.component';
import { SellListComponent } from './sell/sell-list/sell-list.component';
import { OrderItemsComponent } from './frontend/order-items/order-items.component';



const routes: Routes = [
  // Site routes goes here 
  { 
    path: '', 
    component: LoginLayoutComponent ,
    children: [
      { path: 'admin/login', component: UserLogin },
      
      { path: '', component: HomePageComponent },
      { path: 'about', component: AboutPageComponent },
      { path: 'login', component: LoginComponent },
      { path: 'register', component: CustomerComponent },
      { path: 'contact', component: ContactPageComponent },
      { path: 'payment', component: PaymentComponent },
      { path: 'order-report', component: OrderReportComponent },
      { path: 'feedback', component: FeedbackPageComponent },
      { path: 'location', component: LocationComponent },
      { path: 'company', component: CompanyComponent },
      { path: 'center', component: CenterListingComponent },
      { path: 'center/:id', component: CenterListingComponent },
      { path: 'center-details/:id', component: CenterDetailsComponent },
      { path: 'order-items/:id', component: OrderItemsComponent },
      { path: 'center-cart', component: CenterCartComponent },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'my-comments', component: CommentsComponent },
    ]
  },
  { 
    path: '', 
    component: PageLayoutComponent ,
    children: [
      
      { path: 'user/dashboard', component: UserDashboardComponent },
  
      { path: 'admin/company/add', component: CreateCompanyComponent },
      { path: 'admin/company', component: CompanyListComponent },
      { path: 'admin/company/update-company/:id', component: CreateCompanyComponent },


      { path: 'admin/customer', component: CustomerListComponent },
      { path: 'admin/customer/add', component: CreateCustomerComponent },
      { path: 'admin/customer/update-customer/:id', component: CreateCustomerComponent },

      { path: 'admin/location', component: LocationListComponent },
      { path: 'admin/contact', component: ContactsListComponent },
      { path: 'admin/feedback', component: FeedbacksListComponent },
      { path: 'admin/location/add', component: CreateLocationComponent },
      { path: 'admin/location/:id', component: LocationListComponent },
      { path: 'admin/location/update-location/:id', component: CreateLocationComponent },

      { path: 'admin/center', component: CenterListComponent },
      { path: 'admin/center/add', component: CreateCenterComponent },
      { path: 'admin/center/:id', component: CenterListComponent },
      { path: 'admin/center/update-center/:id', component: CreateCenterComponent },

      { path: 'admin/orders', component: OrdersListComponent },

      { path: 'admin/sells/:id', component: SellListComponent },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
