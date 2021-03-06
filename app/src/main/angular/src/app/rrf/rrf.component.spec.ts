import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { RRFComponent } from './rrf.component';

describe('RrfComponent', () => {
  let component: RRFComponent;
  let fixture: ComponentFixture<RRFComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ RRFComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RRFComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
