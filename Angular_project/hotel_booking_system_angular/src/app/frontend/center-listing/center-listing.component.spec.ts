import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CenterListingComponent } from './center-listing.component';

describe('CenterListingComponent', () => {
  let component: CenterListingComponent;
  let fixture: ComponentFixture<CenterListingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CenterListingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CenterListingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
