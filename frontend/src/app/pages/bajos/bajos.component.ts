import { Component, OnInit } from '@angular/core';
import { HttpClientModule,HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Instrumento } from '../../../model/instrumento.model';
import { Subscription } from 'rxjs';
import { InstrumentosService } from '../../services/instrumentos/instrumentos.service';


@Component({
  selector: 'app-bajos',
  templateUrl: './bajos.component.html',
  styleUrls: ['./bajos.component.css'],
  standalone: true,
  imports: [HttpClientModule, CommonModule, FormsModule]
})
export class BajosComponent implements OnInit {
  private subscriptions: Subscription[] = [];
  instrumentos: Instrumento[] = [];

  constructor(private instrumentosService: InstrumentosService) { }

  ngOnInit(): void {
    const tipoInstrumento = 2; // ID para bajos
    const userId = 1; // Reemplaza esto con el ID real del usuario autenticado

    console.log('ID del usuario:', userId); // Log del ID del usuario

    // Llamada al método con los parámetros directamente en las opciones
    this.subscriptions.push(
      this.instrumentosService.getInstrumentoTipoVendedorId(tipoInstrumento).subscribe({
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
