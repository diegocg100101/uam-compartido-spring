import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UeasComponent } from './ueas.component';

describe('UeasComponent', () => {
  let component: UeasComponent;
  let fixture: ComponentFixture<UeasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UeasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UeasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
