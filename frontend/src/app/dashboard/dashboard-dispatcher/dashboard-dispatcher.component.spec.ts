import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardDispatcherComponent } from './dashboard-dispatcher.component';

describe('DashboardDispatcherComponent', () => {
  let component: DashboardDispatcherComponent;
  let fixture: ComponentFixture<DashboardDispatcherComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardDispatcherComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashboardDispatcherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
