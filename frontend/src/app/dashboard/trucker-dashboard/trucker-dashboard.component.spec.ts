import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TruckerDashboardComponent } from './trucker-dashboard.component';

describe('TruckerDashboardComponent', () => {
  let component: TruckerDashboardComponent;
  let fixture: ComponentFixture<TruckerDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TruckerDashboardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TruckerDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
