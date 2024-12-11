import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [HttpClientModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {

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


  viewDetails(eventId: number): void {
    console.log('Visualizando detalhes do evento com ID: ', eventId);
    // Aqui você pode redirecionar para a página de detalhes do evento
    // ou abrir um modal, etc.
  }
}
