import { Component, OnInit } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
})
export class ProductosComponent implements OnInit {
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
