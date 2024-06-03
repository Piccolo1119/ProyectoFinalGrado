import { RouterOutlet, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ProductosComponent } from './pages/productos/productos.component';

export const routes: Routes = [
    
    {path: '', component:HomeComponent},
    {path: 'productos', component: ProductosComponent},


    {path: '**',redirectTo: '',pathMatch:'full' },


];


