import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllmybookComponent } from './allmybook.component';

describe('AllmybookComponent', () => {
  let component: AllmybookComponent;
  let fixture: ComponentFixture<AllmybookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllmybookComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllmybookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
