import { RouterOutlet, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ProductosComponent } from './pages/productos/productos.component';
import { GuitarrasComponent } from './pages/guitarras/guitarras.component';
import { BajosComponent } from './pages/bajos/bajos.component';
import { ProfileComponent } from './auth/profile/profile.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { LoginComponent } from './auth/login/login.component';
import { PersonalDetailsComponent} from './components/personal-details/personal-details.component';
import { PublicarComponent } from './pages/publicar/publicar.component';
import { PedidosComponent } from './pages/pedidos/pedidos.component';
import { PoliticaComponent } from './pages/politica/politica.component';
import { QuienesSomosComponent } from './pages/quienes-somos/quienes-somos.component';
import { RegisterComponent } from './auth/register/register.component';

export const routes: Routes = [
    
    {path: '', component:HomeComponent},
    {path: 'productos', component: ProductosComponent},
    {path: 'guitarras', component: GuitarrasComponent},
    {path: 'bajos', component: BajosComponent},
    {path: 'profile', component:ProfileComponent},
    {path: 'dashboard', component:DashboardComponent},
    {path: 'login', component:LoginComponent},
    {path: 'personal-details', component:PersonalDetailsComponent},
    {path: 'publicar', component:PublicarComponent},
    {path: 'pedidos/:idInstrumento', component:PedidosComponent},
    {path: 'politica', component:PoliticaComponent},
    {path: 'quienes-somos', component:QuienesSomosComponent},
    {path: 'register', component:RegisterComponent},

    {path: '**',redirectTo: '',pathMatch:'full' },


];


