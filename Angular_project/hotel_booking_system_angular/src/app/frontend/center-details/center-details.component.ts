import { CenterService } from "src/app/center/services/center.service"; 
import { LocationService } from "src/app/location/services/location.service"; 
import { Location } from "src/app/location/services/location";
import { Center } from "src/app/center/services/center";
import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from "rxjs/internal/Observable";
import { Comments } from "src/app/center/services/comments";
import { DatePipe } from '@angular/common';
import { Sell } from "src/app/sell/services/sell";
import { SellService } from "src/app/sell/services/sell.service";
import { OrdersService } from "src/app/orders/services/orders.service";
import { Orders } from "src/app/orders/services/orders";

@Component({
  selector: 'app-center-details',
  templateUrl: './center-details.component.html',
  styleUrls: ['./center-details.component.css'],
  providers: [DatePipe]
})
export class CenterDetailsComponent implements OnInit {
  id =""
  order_id = 0;
  user_level_id = window.sessionStorage.user_level_id;
  user_id = window.sessionStorage.user_id;
  center: Center = new Center();
  sellForm: Sell = new Sell();
  orderForm: Orders = new Orders();
  
  commentFrm: Comments = new Comments();
  location: Location = new Location();
  centerList: Observable<Center[]>;
  categories: Observable<Location[]>;
  comments: Observable<any[]>;
  formData = new FormData();
  comment_date = new Date();

  constructor(
    private locationService: LocationService,
    private centerService: CenterService,
    private route: ActivatedRoute,
    private router: Router,
    private datePipe: DatePipe,
    private sellService: SellService,
    private orderService: OrdersService
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    if (this.id) {
      this.getCenter(this.id);
    } 
    this.getLocationList();
    this.getCenterList(0);
    this.getAllComments(this.id);
  }

  onSubmit() {
    this.commentFrm.comments_center_id = this.id;
    this.commentFrm.comments_user_id = window.sessionStorage.user_id;
    this.commentFrm.comments_date = this.datePipe.transform(this.comment_date, 'yyyy-MM-dd');
    this.save(); 
  }



  save() {    
    this.centerService.saveComment(this.commentFrm).subscribe(
      data => {
        console.log(data);
        this.getAllComments(this.id);
        this.commentFrm.comments_title = "";
        this.commentFrm.comments_description = "";
        this.router.navigate(['/center-details/'+this.id]);
      },
      err => {
        // this.errorMessage = err.error.message;
        // this.isSignUpFailed = true;
      }
    );
  }

  getCenter(id): void {
    this.centerService.getCenterDetails(id).subscribe(
      data => {
        console.log(data);
        this.center = data[0];
      },
      err => {
        console.log(err);
      }
    );

  }

  getAllComments(id) {
    this.comments = this.centerService.getAllComments(id);
    console.log(this.comments)
  }

  getLocationList() {
    this.categories = this.locationService.getAllCategories();
  }

  getCenterList(id) {
    this.centerList = this.centerService.getCenterList(id);
  }

  book_venue() {
    console.log("User ID : "+this.user_id)
    if(this.user_id) {
      if(window.sessionStorage.order_id) {
       this.saveSells();
      } else {
        this.saveOrder();
      }
    } else {
      this.router.navigate(['/login']);
    }
  }

  saveSells() {
    this.sellForm.sell_center_id = this.center.center_id;
    this.sellForm.sell_price_per_unit = this.center.center_booking_cost;
    this.sellForm.sell_order_id = window.sessionStorage.order_id;
    this.sellForm.sell_units = 1
    this.sellForm.sell_total_cost = this.center.center_booking_cost;
    this.addItem();
  }

  addItem(): void {
    console.log(this.sellForm);
    this.sellService.createSell(this.sellForm).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['/payment']);
      },
      err => {
        // this.errorMessage = err.error.message;
        // this.isSignUpFailed = true;
      }
    );
  }

  saveOrder(): void {
    let myDate = new Date();
    let todayDate = this.datePipe.transform(myDate, 'dd MMM yyyy hh:mm a');
    console.log("Date = "+todayDate);
    this.orderForm.order_date = todayDate;
    this.orderForm.order_customer_id = this.user_id;
    this.orderForm.order_total = 0;
    this.orderForm.order_status = "In Progress";
    this.addOrder();
      
  }

  addOrder(): void {
    this.orderService.createOrders(this.orderForm).subscribe(
      data => {
        console.log(data);
        window.sessionStorage.order_id = data['order_id'];
        console.log("Order ID : "+window.sessionStorage.order_id);
        this.saveSells();
      },
      err => {
        // this.errorMessage = err.error.message;
        // this.isSignUpFailed = true;
      }
    );
  }

}
