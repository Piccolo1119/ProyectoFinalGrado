import { Component, OnInit } from '@angular/core';
import { InstrumentosService } from '../../services/instrumentos/instrumentos.service';
import { Subscription } from 'rxjs';
import { Instrumento } from '../../../model/instrumento.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-guitarras',
  templateUrl: './guitarras.component.html',
  styleUrls: ['./guitarras.component.css'],
  standalone: true,
  imports: [CommonModule],
  // No es necesario importar HttpClientModule, CommonModule o FormsModule aquí si ya están importados globalmente
})
export class GuitarrasComponent implements OnInit {
  private subscriptions: Subscription[] = [];
  instrumentos: Instrumento[] = [];

  constructor(private instrumentosService: InstrumentosService) { }

  ngOnInit(): void {
    const tipoInstrumento = 1; // ID para guitarras
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
