import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PedidosService {
  private apiUrl = 'http://tu-api.com/api/pedidos'; // Reemplaza esta URL con la URL de tu API

  constructor(private http: HttpClient) { }

  createPedido(pedido: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, pedido);
  }

  // Otros métodos para actualizar, obtener o eliminar pedidos si son necesarios
}
