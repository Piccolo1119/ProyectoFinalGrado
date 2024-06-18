import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { InstrumentosService } from '../../services/instrumentos/instrumentos.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  standalone: true,
  imports: [FormsModule, CommonModule],
})

export class HeaderComponent implements OnInit {
  terminoBusqueda: string = '';
  resultados: any[] = [];
  todosInstrumentos: any[] = [];

  constructor(private instrumentosService: InstrumentosService) { }

  ngOnInit(): void {
    const userId = 1; // Reemplaza esto con el ID real del usuario autenticado
    console.log('ID del usuario:', userId); // Log del ID del usuario
    this.instrumentosService.getInstrumentosByVendedorIdNot(userId).subscribe({
      next: (data: any[]) => {
        console.log('Instrumentos cargados:', data); // Depuración
        this.todosInstrumentos = data;
      },
      error: (err) => {
        console.error('Error fetching instruments:', err);
      }
    });
  }

  filtrarInstrumentos() {
    console.log('Término de búsqueda:', this.terminoBusqueda); // Depuración
    if (this.terminoBusqueda) {
        const terminoBusquedaLower = this.terminoBusqueda.toLowerCase();
        this.resultados = this.todosInstrumentos.filter(instrumento => 
            (instrumento.nombre && instrumento.nombre.toLowerCase().includes(terminoBusquedaLower)) ||
            (instrumento.marca && instrumento.marca.toLowerCase().includes(terminoBusquedaLower))
        );
        console.log('Resultados filtrados:', this.resultados); // Depuración
    } else {
        this.resultados = [];
    }
}

  buscar() {
    // Implementa la lógica de búsqueda si es necesario
    console.log('Buscando:', this.terminoBusqueda);
  }
}