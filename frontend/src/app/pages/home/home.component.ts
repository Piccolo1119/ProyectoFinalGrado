import { HttpClientModule,HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
})
export class HomeComponent implements OnInit {
  productos: any[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<any[]>('http://localhost:8080/instrumentos').subscribe(data => {
      // Transforma las rutas de las imágenes para que sean accesibles desde el navegador
      this.productos = data.map(producto => {
        return {
          ...producto,
          imagen: producto.imagen.replace('C:\\JULIOdocs\\DAW2º\\TFG-Julio\\backend\\myapp\\src\\assets\\images', '/assets/images')
        };
      });
    });
  }
}
