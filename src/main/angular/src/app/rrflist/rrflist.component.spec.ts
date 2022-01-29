import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { RRFListComponent } from './rrflist.component';

describe('RRFListComponent', () => {
  let component: RRFListComponent;
  let fixture: ComponentFixture<RRFListComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ RRFListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RRFListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
