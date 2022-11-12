import { LocationService } from "src/app/location/services/location.service"; 
import { Location } from "src/app/location/services/location";
import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from "rxjs/internal/Observable";

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css']
})
export class LocationComponent implements OnInit {
  categories: Observable<Location[]>;
  user_level_id = window.sessionStorage.user_level_id;

  constructor(private locationService: LocationService,
    private route: ActivatedRoute,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.categories = this.locationService.getAllCategories();
  }

  public openNewTab(location) {
    window.open(location, '_blank');
  }

  deleteLocation(id: number) {
    this.locationService.deleteLocation(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

}
