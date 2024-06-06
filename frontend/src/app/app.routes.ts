import { RouterOutlet, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ProductosComponent } from './pages/productos/productos.component';
import { GuitarrasComponent } from './pages/guitarras/guitarras.component';
import { BajosComponent } from './pages/bajos/bajos.component';
import { ProfileComponent } from './auth/profile/profile.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { LoginComponent } from './auth/login/login.component';
import {PersonalDetailsComponent} from './components/personal-details/personal-details.component';

export const routes: Routes = [
    
    {path: '', component:HomeComponent},
    {path: 'productos', component: ProductosComponent},
    {path: 'guitarras', component: GuitarrasComponent},
    {path: 'bajos', component: BajosComponent},
    {path: 'profile', component:ProfileComponent},
    {path: 'dashboard', component:DashboardComponent},
    {path: 'login', component:LoginComponent},
    {path: 'personal-details', component:PersonalDetailsComponent},

    {path: '**',redirectTo: '',pathMatch:'full' },


];


