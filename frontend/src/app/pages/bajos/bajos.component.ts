import { Component, OnInit } from '@angular/core';
import { HttpClientModule,HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-bajos',
  templateUrl: './bajos.component.html',
  styleUrls: ['./bajos.component.css'],
  standalone: true,
  imports: [HttpClientModule, CommonModule, FormsModule]
})
export class BajosComponent implements OnInit {
  bajos: any[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    // Filtrar por el tipo de instrumento (id=1 para guitarras)
    this.http.get<any[]>('http://localhost:8080/findByTipoinstrumentos', { params: { tipo_instrumento: '2' } }).subscribe(data => {
      // Transforma las rutas de las imágenes para que sean accesibles desde el navegador
      this.bajos = data.map(instrumento => {
        return {
          ...instrumento,
          imagen: instrumento.imagen.replace('C:\\JULIOdocs\\DAW2º\\TFG-Julio\\backend\\myapp\\src\\assets\\images', '/assets/images')
        };
      });
    });
  }
}
