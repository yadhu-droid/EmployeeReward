import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PointsaddComponent } from './pointsadd.component';

describe('PointsaddComponent', () => {
  let component: PointsaddComponent;
  let fixture: ComponentFixture<PointsaddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PointsaddComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PointsaddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
