import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewemployeesComponent } from './viewemployees.component';

describe('ViewemployeesComponent', () => {
  let component: ViewemployeesComponent;
  let fixture: ComponentFixture<ViewemployeesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewemployeesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewemployeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
