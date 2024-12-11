import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  constructor(private router: Router) {}

  
  goToHome() {
    this.router.navigate(['/home']);
  }

  goToList() {
    this.router.navigate(['/list']);
  }

  goToContact() {
    this.router.navigate(['/contact']);
  }

  goToUser() {
    this.router.navigate(['/user']);
  }

  goToEvent() {
    this.router.navigate(['/event']);
  }
}
