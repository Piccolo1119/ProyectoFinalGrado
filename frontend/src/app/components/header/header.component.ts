import { Component } from '@angular/core';
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
export class HeaderComponent {
  terminoBusqueda: string = '';
  resultadosBusqueda: any[] = [];

  constructor(private instrumentosService: InstrumentosService) { }

  buscar() {
    if (this.terminoBusqueda.trim() !== '') {
      this.instrumentosService.getInstrumentos().subscribe(instrumentos => {
        this.resultadosBusqueda = instrumentos.filter(instrumento =>
          instrumento.nombre.toLowerCase().includes(this.terminoBusqueda.toLowerCase())
        );
      });
    } else {
      this.resultadosBusqueda = [];
    }
  }
}
