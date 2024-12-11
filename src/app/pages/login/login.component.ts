import { Component } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { Router } from '@angular/router';
import {FormGroup, FormControl, ReactiveFormsModule, Validators} from '@angular/forms';
import { LoginService } from '../../service/login.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule,HttpClientModule],
  providers: [LoginService],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  profileForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)]),
  });

  constructor(private loginService: LoginService, private router: Router) {}

  onLogin() {
    if (this.profileForm.valid) {
      const email = this.profileForm.value.email as string;
      const password = this.profileForm.value.password as string;

      this.loginService.login(email, password).subscribe({
        next: () => {
          console.log('Login realizado com sucesso!', { email, password });
          this.router.navigate(['/home']); // redireciona para a página desejada após login
        },
        error: (err) => console.error('Erro no login:', err)
      });
    } else {
      console.log('Formulário inválido');
    }
  }

}
