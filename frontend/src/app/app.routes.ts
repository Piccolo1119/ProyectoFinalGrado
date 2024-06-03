import { RouterOutlet, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ProductosComponent } from './pages/productos/productos.component';
import { GuitarrasComponent } from './pages/guitarras/guitarras.component';
import { BajosComponent } from './pages/bajos/bajos.component';

export const routes: Routes = [
    
    {path: '', component:HomeComponent},
    {path: 'productos', component: ProductosComponent},
    {path: 'guitarras', component: GuitarrasComponent},
    {path: 'bajos', component: BajosComponent},


    {path: '**',redirectTo: '',pathMatch:'full' },


];


