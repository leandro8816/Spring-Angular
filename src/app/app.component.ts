import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { EventsDisplayComponent } from './pages/events-display/events-display.component';
import { LoginComponent } from './pages/login/login.component';
import { NavbarComponent } from './components/navbar/navbar.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent,RouterModule ],
  providers:[NavbarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'event';
}
