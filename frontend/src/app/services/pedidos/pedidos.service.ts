import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pedidos } from '../../../model/pedidos.model';

@Injectable({
  providedIn: 'root'
})
export class PedidosService {

  constructor(private http: HttpClient) { }

  getPedidosByComprador(comprador: number): Observable<Pedidos[]> {
    return this.http.get<Pedidos[]>('http://localhost:8080/pedidos/user');
  }

  // Otros métodos para actualizar, obtener o eliminar pedidos si son necesarios
}
