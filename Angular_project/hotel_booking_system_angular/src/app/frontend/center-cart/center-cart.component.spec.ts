import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CenterCartComponent } from './center-cart.component';

describe('CenterCartComponent', () => {
  let component: CenterCartComponent;
  let fixture: ComponentFixture<CenterCartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CenterCartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CenterCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
