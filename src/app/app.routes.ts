import { Routes } from '@angular/router';
import { EventsDisplayComponent } from './pages/events-display/events-display.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { UserDisplayComponent } from './pages/user-display/user-display.component';
import { UserFormsComponent } from './pages/user-forms/user-forms.component';
import { HomeComponent } from './components/home/home.component';
import { EventsFormsComponent } from './pages/events-forms/events-forms.component';

export const routes: Routes = [
    {
        path: "list",
        component: EventsDisplayComponent
    },
    {
        path: "",
        component: LoginComponent
    },
    {
        path: "signup",
        component: SignupComponent
    },
    {
        path: "users",
        component: UserDisplayComponent
    },
    {
        path: 'users/edit/:id',
        component: UserFormsComponent
    }
    ,
    {
        path: 'home',
        component: HomeComponent
    }
    ,
    {
        path: 'event',
        component: EventsFormsComponent
    },
    { path: '', redirectTo: '/home', pathMatch: 'full' }, // Redireciona para "home" por padrão
    { path: '**', redirectTo: '/home' } // Redireciona para "home" se a rota não for encontrada
    
];
