import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateLocationComponent } from './location-add/create-location.component';
import { LocationListComponent } from './location-list/location-list.component';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // <== add the imports!
import {RouterModule} from '@angular/router';


@NgModule({
  declarations: [
    CreateLocationComponent,
    LocationListComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule
  ],
  bootstrap: [
    LocationListComponent
  ]
})
export class LocationModule { }
