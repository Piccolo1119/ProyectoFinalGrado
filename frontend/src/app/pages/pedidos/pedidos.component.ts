import { Component, Input, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { PedidosService } from '../../services/pedidos/pedidos.service';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router'; // Importar el enrutador
import { InstrumentosService } from '../../services/instrumentos/instrumentos.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-create-pedido',
  templateUrl: './pedidos.component.html',
  styleUrls: ['./pedidos.component.css'],
  standalone: true,
  imports: [FormsModule, CommonModule],
})
export class PedidosComponent implements OnInit {
  pedido: any = {};
  showSuccessMessage = false;
  redirectToHome = false;
  mensajeExito: string | undefined;
  mensajeError: string | undefined;

  constructor(private http: HttpClient, private router: Router, private instrumentosService : InstrumentosService) {}

  @Input() idInstrumento: number = 0;

  ngOnInit(): void {
    this.http.get<any[]>('http://localhost:8080/instrumentos/' + this.idInstrumento).subscribe(data => {
      // Transforma las rutas de las imágenes para que sean accesibles desde el navegador
      this.pedido.producto = data;
      this.codigoPedido();
      this.calcularMonto();
    });
  }

  calcularMonto() {
    const gastosGestion = 5.50;
    this.pedido.monto = this.pedido.producto.precio + gastosGestion;
  }

  codigoPedido() {
    const caracteres = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    let codigo = '';
    for (let i = 0; i < 6; i++) {
      const indice = Math.floor(Math.random() * caracteres.length);
      codigo += caracteres.charAt(indice);
    }
    this.pedido.codigo = codigo;
    console.log('Código de pedido:', this.pedido.codigo);
  }

  submitPedido() {
    const pedido = {
      estadoPago: this.pedido.estadoPago,
      fecha: this.pedido.fecha,
      monto: this.pedido.monto,
      comprador: this.pedido.comprador,
      instrumento: {
        id: this.pedido.producto.id
      },
      codigo: this.pedido.codigo
    };

    this.http.post('http://localhost:8080/addPedidos', pedido).subscribe(
      (response) => {
        if (response) {
          console.log(response);
          this.mensajeExito = 'Pedido creado con éxito.';
          console.log('Instrumento desactivado:', this.pedido.producto.id);
        } else {
          console.error('Error al crear el pedido');
        }
      },
      (error) => {
        console.error('Ha ocurrido un error en la solicitud HTTP:', error);
        this.mensajeError = 'Error al crear el pedido. Rellena correctamente los campos.';
      }
    );
  }
}
