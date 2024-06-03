import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BajosComponent } from './bajos.component';

describe('BajosComponent', () => {
  let component: BajosComponent;
  let fixture: ComponentFixture<BajosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BajosComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BajosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
