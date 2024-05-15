import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationByAdminComponent } from './registration-by-admin.component';

describe('RegistrationByAdminComponent', () => {
  let component: RegistrationByAdminComponent;
  let fixture: ComponentFixture<RegistrationByAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrationByAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistrationByAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
