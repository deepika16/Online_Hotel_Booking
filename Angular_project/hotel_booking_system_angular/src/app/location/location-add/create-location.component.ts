import { LocationService } from '../services/location.service';
import { Router, ActivatedRoute } from '@angular/router';


import { Location } from '../services/location';


import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-location',
  templateUrl: './create-location.component.html',
  styleUrls: ['./create-location.component.css']
})
export class CreateLocationComponent implements OnInit {
  

  location: Location = new Location();
  isUpdate = false;
  formData = new FormData();
  file: File;
  submitted = false;
  

  constructor(
    private locationService: LocationService,
    private route: ActivatedRoute,
    private router: Router,
  ) { 
    console.log("I am here");

  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    console.log("I am here location");
    if (id) {
      this.isUpdate = true;
      this.getLocation(id);
    }
  }

  getLocation(id): void {
    this.locationService.getLocation(id).subscribe(
      data => {
        console.log(data);
        this.location = data;
      },
      err => {
        console.log(err);
      }
    );

  }
 

  newLocation(): void {
    this.submitted = false;
    this.location = new Location();
  }

  // File Upload Logic
  fileChange(event) {
    console.log("Inside file upload")
    let fileList: FileList = event.target.files;
    if(fileList.length > 0) {
        this.file = fileList[0];
        this.formData.append('location_image', this.file, this.file.name);
    }
}

  save() {    
    // Put location form in FormData
    for (let key in this.location) {
      console.log("Insie Iterator")
      this.formData.append(key, this.location[key]);
    }
   
    this.locationService.createLocation(this.formData).subscribe(
      data => {
        console.log(data);
        // this.isSuccessful = true;
        // this.isSignUpFailed = false;
        this.router.navigate(['/admin/location']);
      },
      err => {
        // this.errorMessage = err.error.message;
        // this.isSignUpFailed = true;
      }
    );
  }

  onSubmit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.updateLocation();
    } else {
      this.submitted = true;
      this.save();
    }
    
  }

  updateLocation(): void {
    
    // Put location form in FormData ///
    for (let key in this.location) {
      console.log("Insie Iterator")
      this.formData.append(key, this.location[key]);
    }
    if(this.file) {
      console.log("I am here");
      // End of Request ///
      this.locationService.updateUploadLocation(this.location.location_id, this.formData).subscribe(
        data => {
          console.log(data);
          // this.isSuccessful = true;
          // this.isSignUpFailed = false;
          this.router.navigate(['/admin/location']);
        },
        err => {
          // this.errorMessage = err.error.message;
          // this.isSignUpFailed = true;
        }
      );
    } else {
      // End of Request ///
      this.locationService.updateLocation(this.location.location_id, this.location).subscribe(
        data => {
          console.log(data);
          // this.isSuccessful = true;
          // this.isSignUpFailed = false;
          this.router.navigate(['/admin/location']);
        },
        err => {
          // this.errorMessage = err.error.message;
          // this.isSignUpFailed = true;
        }
      );
    }
    
  }
}
