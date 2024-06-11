// create-pedido.component.ts
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { PedidosService } from '../../services/pedidos/pedidos.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-create-pedido',
  templateUrl: './pedidos.component.html',
  styleUrls: ['./pedidos.component.css'],
  standalone : true,
  imports: [FormsModule],
})
export class PedidosComponent implements OnInit {
  pedido: any = {};

  constructor(private pedidosService: PedidosService) { }

  ngOnInit(): void {
    // Aquí podrías cargar datos adicionales para el pedido, como los usuarios disponibles para seleccionar como comprador o instrumentos disponibles, etc.
  }

  submitPedido() {
    this.pedidosService.createPedido(this.pedido).subscribe(
      (response: any) => {
        console.log("Pedido creado con éxito:", response);
        // Aquí podrías redirigir al usuario a otra página o realizar otras acciones después de crear el pedido
      },
      (error: any) => {
        console.error("Error al crear el pedido:", error);
        // Aquí podrías mostrar un mensaje de error al usuario
      }
    );
  }
}
