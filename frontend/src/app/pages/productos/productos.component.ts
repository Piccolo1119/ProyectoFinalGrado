import { Component, OnInit } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { InstrumentosService } from '../../services/instrumentos/instrumentos.service';
import { Subscription } from 'rxjs';
import { Instrumento } from '../../../model/instrumento.model';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
})
export class ProductosComponent implements OnInit {
  private subscriptions: Subscription[] = [];
  instrumentos: Instrumento[] = [];

  constructor(private http: HttpClient, private instrumentosService: InstrumentosService,) { }

  ngOnInit(): void {
    const userId = 1; // Reemplaza esto con el ID real del usuario autenticado
    console.log('ID del usuario:', userId); // Log del ID del usuario
    this.subscriptions.push(
      this.instrumentosService.getInstrumentosByVendedorIdNot(userId).subscribe({
        next: (instruments) => {
          this.instrumentos = instruments;
          console.log('Instrumentos devueltos:', instruments); // Log de los instrumentos devueltos
        },
        error: (err) => {
          console.error('Error fetching instruments:', err);
        }
      })
    );
  }

}
