import { Component, OnInit } from '@angular/core';
import { HttpClientModule,HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-guitarras',
  templateUrl: './guitarras.component.html',
  styleUrls: ['./guitarras.component.css'],
  standalone: true,
  imports: [HttpClientModule, CommonModule, FormsModule]
})
export class GuitarrasComponent implements OnInit {
  guitarras: any[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    // Filtrar por el tipo de instrumento (id=1 para guitarras)
    this.http.get<any[]>('http://localhost:8080/findByTipoinstrumentos', { params: { tipo_instrumento: '1' } }).subscribe(data => {
      // Transforma las rutas de las imágenes para que sean accesibles desde el navegador
      this.guitarras = data.map(instrumento => {
        return {
          ...instrumento,
          imagen: instrumento.imagen.replace('C:\\JULIOdocs\\DAW2º\\TFG-Julio\\backend\\myapp\\src\\assets\\images', '/assets/images')
        };
      });
    });
  }
}
