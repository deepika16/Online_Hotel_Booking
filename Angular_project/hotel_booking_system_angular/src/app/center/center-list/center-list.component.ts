import { CenterService } from "../services/center.service";
import { Center } from "../services/center";
import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from "rxjs/internal/Observable";

@Component({
  selector: "app-center-list",
  templateUrl: "./center-list.component.html",
  styleUrls: ["./center-list.component.css"]
})
export class CenterListComponent implements OnInit {
  center: Observable<Center[]>;
  user_level_id = window.sessionStorage.user_level_id;

  constructor(private centerService: CenterService,
    private route: ActivatedRoute,
    private router: Router) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if(id) {
      this.reloadData(id);
    } else {
      this.reloadData(0);
    }
  }

  reloadData(id) {
    this.center = this.centerService.getCenterList(id);
  }

  public openNewTab(location) {
    window.open(location, '_blank');
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
}
