import { CenterService } from "src/app/center/services/center.service"; 
import { LocationService } from "src/app/location/services/location.service"; 
import { Location } from "src/app/location/services/location";
import { Center } from "src/app/center/services/center";
import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from "rxjs/internal/Observable";

@Component({
  selector: 'app-center-listing',
  templateUrl: './center-listing.component.html',
  styleUrls: ['./center-listing.component.css']
})
export class CenterListingComponent implements OnInit {

  center: Observable<Center[]>;
  search_text = "";
  categories: Observable<Location[]>;
  user_level_id = window.sessionStorage.user_level_id;

  constructor(private centerService: CenterService,
    private locationService: LocationService,
    private route: ActivatedRoute,
    private router: Router) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if(id) {
      this.reloadData(id);
    } else {
      this.reloadData(0);
    }
    this.getLocationList();
  }

  reloadData(id) {
    this.center = this.centerService.getCenterList(id);
  }
  

  public openNewTab(location) {
    window.open(location, '_blank');
  }

  searchHotel() {
    if(this.search_text == "") {
      this.center = this.centerService.filterCenterList("0");
    } else {
      this.center = this.centerService.filterCenterList(this.search_text);
    }    
  }

  resetSearch() {
    this.center = this.centerService.filterCenterList("0");
  }

  deleteCenter(id: number) {
    this.centerService.deleteCenter(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData(0);
        },
        error => console.log(error));
  }

  getLocationList() {
    this.categories = this.locationService.getAllCategories();
  }

}
