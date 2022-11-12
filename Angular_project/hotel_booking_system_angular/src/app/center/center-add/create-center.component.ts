import { CenterService } from '../services/center.service';
import { LocationService } from '../../location/services/location.service';
import { Router, ActivatedRoute } from '@angular/router';


import { Center } from '../services/center';
import { Location } from '../../location/services/location';



import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-center',
  templateUrl: './create-center.component.html',
  styleUrls: ['./create-center.component.css']
})
export class CreateCenterComponent implements OnInit {
  

  center: Center = new Center();
  location: Location = new Location();
  submitted = false;
  isUpdate = false;
  formData = new FormData();
  file: File;
  

  constructor(
    private locationService: LocationService,
    private centerService: CenterService,
    private route: ActivatedRoute,
    private router: Router,
  ) { 
    console.log("I am here");

  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    console.log("I am here center");
    if (id) {
      this.isUpdate = true;
      this.getCenter(id);
    } else {
      this.center.center_location_id = "0";
    }
    this.getLocationOption();
  }

  getCenter(id): void {
    this.centerService.getCenter(id).subscribe(
      data => {
        console.log(data);
        this.center = data;
      },
      err => {
        console.log(err);
      }
    );

  }
  
  getLocationOption(): void {
    this.locationService.getAllCategories().subscribe(
      data => {
        console.log(data);
        this.location = data;
      },
      err => {
        console.log(err);
      }
    );
  }
 

  newCenter(): void {
    this.submitted = false;
    this.center = new Center();
  }

  // File Upload Logic
  fileChange(event) {
    console.log("Inside file upload")
    let fileList: FileList = event.target.files;
    if(fileList.length > 0) {
        this.file = fileList[0];
        this.formData.append('center_image', this.file, this.file.name);
    }
}

  save() {    
    // Put center form in FormData
    for (let key in this.center) {
      console.log("Insie Iterator")
      this.formData.append(key, this.center[key]);
    }
   
    this.centerService.createCenter(this.formData).subscribe(
      data => {
        console.log(data);
        // this.isSuccessful = true;
        // this.isSignUpFailed = false;
        this.router.navigate(['/admin/center']);
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
      this.updateCenter();
    } else {
      this.submitted = true;
      this.save();
    }
    
  }

  updateCenter(): void {
    
    // Put center form in FormData ///
    for (let key in this.center) {
      console.log("Insie Iterator")
      this.formData.append(key, this.center[key]);
    }
    if(this.file) {
      console.log("I am here");
      // End of Request ///
      this.centerService.updateUploadCenter(this.center.center_id, this.formData).subscribe(
        data => {
          console.log(data);
          // this.isSuccessful = true;
          // this.isSignUpFailed = false;
          this.router.navigate(['/admin/center']);
        },
        err => {
          // this.errorMessage = err.error.message;
          // this.isSignUpFailed = true;
        }
      );
    } else {
      // End of Request ///
      this.centerService.updateCenter(this.center.center_id, this.center).subscribe(
        data => {
          console.log(data);
          // this.isSuccessful = true;
          // this.isSignUpFailed = false;
          this.router.navigate(['/admin/center']);
        },
        err => {
          // this.errorMessage = err.error.message;
          // this.isSignUpFailed = true;
        }
      );
    }
    
  }
}
