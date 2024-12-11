import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../../service/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-user-forms',
  standalone: true,
  imports: [ReactiveFormsModule,HttpClientModule],
  providers:[UserService],
  templateUrl: './user-forms.component.html',
  styleUrl: './user-forms.component.scss'
})
export class UserFormsComponent implements OnInit {

  userForm  = new FormGroup({
    name: new FormControl ('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
  
  });
  userId!: number;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    
  }

  ngOnInit(): void {
    // Obtém o ID do utilizador da rota
    this.userId = Number(this.route.snapshot.paramMap.get('id'));
    
    
    
  
  }
  onSubmit(): void {
    if (this.userForm.valid) {
      this.userService.updateUser(this.userId, this.userForm.value).subscribe(() => {
        
        this.router.navigate(['/users']);
      });
    }
  }
}

  

