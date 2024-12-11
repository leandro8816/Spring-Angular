import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private apiUrl = 'http://localhost:8080/api/v1/events';
  private readonly token: string = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbWFpbEBleGVtcGxvLmNvbSIsImlhdCI6MTczMDI5NzU1OCwiZXhwIjoxNzMwOTAyMzU4fQ.FpGzF3WFpqwxsOjxYgo3IxZUmqInr0q1bMQk03NtUDw';
  
  constructor(private http: HttpClient) {}

  createEvent(form : any): Observable<any> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.token}` // Adiciona o cabeçalho de autorização com o token
    });


    return this.http.post(this.apiUrl, form,{ headers });
  }
}
