import { Component, OnInit } from '@angular/core';
import { EventService } from '../../service/event.service';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-events-forms',
  standalone: true,
  imports: [ReactiveFormsModule,HttpClientModule],
  providers:[EventService],
  templateUrl: './events-forms.component.html',
  styleUrl: './events-forms.component.scss'
})
export class EventsFormsComponent implements OnInit {

  eventForm = new FormGroup({
    name: new FormControl('', Validators.required),
    description: new FormControl('', Validators.required),
    datetime: new FormControl('', Validators.required),
    location: new FormControl('', Validators.required),
    organizer: new FormControl('', Validators.required),
    image: new FormControl(null)
  });
  selectedImage: File | null = null;

  constructor(private eventService: EventService) {}

  ngOnInit(): void {}

  onImageSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.selectedImage = file;
      this.eventForm.patchValue({ image: file });
    }
  }

  onSubmit(): void {
    if (this.eventForm.valid && this.selectedImage) {
      const formData = new FormData();
      formData.append('name', this.eventForm.get('name')?.value || '');
      formData.append('description', this.eventForm.get('description')?.value || '');
      formData.append('datetime', this.eventForm.get('datetime')?.value || '');
      formData.append('location', this.eventForm.get('location')?.value || '');
      formData.append('organizer', this.eventForm.get('organizer')?.value || '');
      formData.append('image', this.selectedImage);

      this.eventService.createEvent(formData).subscribe(
        response => {
          console.log('Evento criado com sucesso', response);
          this.eventForm.reset();
          this.selectedImage = null;
        },
        error => {
          console.error('Erro ao criar evento', error);
        }
      );
    }
  }

}
