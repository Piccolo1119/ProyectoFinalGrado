import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InstrumentosService {

  constructor(private http: HttpClient) { }

  // Método para obtener todos los instrumentos
  getInstrumentos(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8080/instrumentos');
  }

  /* // Método para obtener un instrumento por ID
  getInstrumentoById(id: string): Observable<any> {
    return this.http.get<any>(`URL_DEL_BACKEND/instrumentos/${id}`);
  } */

  // Método para agregar un nuevo instrumento
  agregarInstrumento(instrumento: any): Observable<any> {
    return this.http.post<any>('http://localhost:8080/addInstrumentos', instrumento);
  }

  // Método para actualizar un instrumento existente
  actualizarInstrumento(id: string, instrumento: any): Observable<any> {
    return this.http.put<any>(`http://localhost:8080/editInstrumentos/${id}`, instrumento);
  }

  // Método para eliminar un instrumento
  eliminarInstrumento(id: string): Observable<any> {
    return this.http.delete<any>(`http://localhost:8080/deleteInstrumentos/${id}`);
  }
}
