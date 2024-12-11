import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8080/api/v1/users'; // URL da API para gerir utilizadores
  private readonly token: string = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbWFpbEBleGVtcGxvLmNvbSIsImlhdCI6MTczMDI5NzU1OCwiZXhwIjoxNzMwOTAyMzU4fQ.FpGzF3WFpqwxsOjxYgo3IxZUmqInr0q1bMQk03NtUDw';
  
  constructor(private http: HttpClient) {
    
    
  }
  

  // Obter lista de utilizadores
  getUsers(): Observable<any> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.token}` // Adiciona o cabeçalho de autorização com o token
    });
    
    return this.http.get<any>(this.apiUrl, { headers });
  }

  // Adicionar um novo utilizador
  addUser(user: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, user);
  }

  // Editar um utilizador existente
  updateUser(userId: number, user: any): Observable<any> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.token}` // Adiciona o cabeçalho de autorização com o token
    });
    const userData = {
      id: 2, 
      firstname: "Novo",
      lastname: "ze",
      email: "novoemail@example.com"
  };
    console.log("URL:", `${this.apiUrl}/${userId}`);
  console.log("Dados do utilizador:", user);
  console.log("Headers:", headers);
    return this.http.patch<any>(`${this.apiUrl}/${userId}`, userData, { headers });
  }

  // Eliminar um utilizador
  deleteUser(userId: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${userId}`);
  }
}
