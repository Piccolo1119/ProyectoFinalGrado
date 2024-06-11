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
    this.instrumentosService.getInstrumentos().subscribe((data: any[]) => {
      console.log('Instrumentos cargados:', data); // Depuración
      this.todosInstrumentos = data;
    });
  }

  filtrarInstrumentos() {
    console.log('Término de búsqueda:', this.terminoBusqueda); // Depuración
    if (this.terminoBusqueda) {
      this.resultados = this.todosInstrumentos.filter(instrumento => 
        instrumento.nombre && instrumento.nombre.toLowerCase().includes(this.terminoBusqueda.toLowerCase())
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