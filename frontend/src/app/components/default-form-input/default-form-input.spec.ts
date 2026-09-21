import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DefaultFormInput } from './default-form-input';

describe('DefaultFormInput', () => {
  let component: DefaultFormInput;
  let fixture: ComponentFixture<DefaultFormInput>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DefaultFormInput],
    }).compileComponents();

    fixture = TestBed.createComponent(DefaultFormInput);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
