import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateCenterComponent } from './center-add/create-center.component';
import { CenterListComponent } from './center-list/center-list.component';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // <== add the imports!
import {RouterModule} from '@angular/router';


@NgModule({
  declarations: [
    CreateCenterComponent,
    CenterListComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule
  ],
  bootstrap: [
    CenterListComponent
  ]
})
export class CenterModule { }
