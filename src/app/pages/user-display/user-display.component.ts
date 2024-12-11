import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user.service';
import { Router } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-user-display',
  standalone: true,
  imports: [HttpClientModule],
  providers: [UserService],
  templateUrl: './user-display.component.html',
  styleUrl: './user-display.component.scss'
})
export class UserDisplayComponent implements OnInit {
  users: any[] = [];

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getUsers().subscribe({
      next: (data) => {
        this.users = data;
        console.log(data)
      },
      error: (err) => console.error('Erro ao carregar utilizadores:', err)
    });
  }

  addUser(): void {
    this.router.navigate(['/add-user']); 
  }

  editUser(userId: number): void {
    this.router.navigate(['users/edit/', userId]); 
  }

  deleteUser(userId: number): void {
    this.userService.deleteUser(userId).subscribe({
      next: () => {
        console.log('Utilizador eliminado com sucesso');
        this.loadUsers(); 
      },
      error: (err) => console.error('Erro ao eliminar utilizador:', err)
    });
  }
}
