import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';

@Component({
  selector: 'app-events-display',
  standalone: true,
  imports: [HttpClientModule],
  templateUrl: './events-display.component.html',
  styleUrls: ['./events-display.component.scss'] // Corrigi de styleUrl para styleUrls
})
export class EventsDisplayComponent implements OnInit {
  httpClient = inject(HttpClient);
  data: any[] = [];

  // Obtenha o token de um serviço ou armazenamento se necessário
  private readonly token: string = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbWFpbEBleGVtcGxvLmNvbSIsImlhdCI6MTczMDI5NzU1OCwiZXhwIjoxNzMwOTAyMzU4fQ.FpGzF3WFpqwxsOjxYgo3IxZUmqInr0q1bMQk03NtUDw';
  
  ngOnInit(): void {
    this.fetchData();
  }

  fetchData() {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.token}` // Adiciona o cabeçalho de autorização com o token
    });

    this.httpClient.get('http://localhost:8080/api/v1/events', { headers })
      .subscribe({
        next: (data: any) => {
          console.log(data);
          this.data = data;
        },
        error: (error) => {
          console.error('Erro ao buscar dados:', error);
          // Adiciona um log de erro 
        }
      });
  }
}
