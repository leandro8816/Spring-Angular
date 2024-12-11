import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventsFormsComponent } from './events-forms.component';

describe('EventsFormsComponent', () => {
  let component: EventsFormsComponent;
  let fixture: ComponentFixture<EventsFormsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EventsFormsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EventsFormsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
