import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardTruckerComponent } from './dashboard-trucker.component';

describe('DashboardTruckerComponent', () => {
  let component: DashboardTruckerComponent;
  let fixture: ComponentFixture<DashboardTruckerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardTruckerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashboardTruckerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
