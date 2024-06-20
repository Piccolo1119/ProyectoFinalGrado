import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Instrumento } from '../../../model/instrumento.model';

@Injectable({
  providedIn: 'root'
})

export class InstrumentosService {

  constructor(private http: HttpClient) { }

  getInstrumentos(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8080/instrumentos');
  }
  getInstrumentosActivos(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8080/instrumentos/activos');
  }

  getInstrumentosById(idInstrumento: number): Observable<Instrumento[]> {
    return this.http.get<Instrumento[]>('http://localhost:8080/instrumentos/' + idInstrumento);
  }

  getInstrumentosByVendedorId(vendedorId: number): Observable<Instrumento[]> {
    return this.http.get<Instrumento[]>('http://localhost:8080/instrumentos/user');
  }

  getInstrumentosByVendedorIdNot(vendedorId: number): Observable<Instrumento[]> {
    return this.http.get<Instrumento[]>('http://localhost:8080/instrumentos/usernot');
  }

  deleteInstrumento(id: number): Observable<any> {
    return this.http.delete<any>('http://localhost:8080/deleteInstrumentos/' + id);
  }

  editInstrumento(instrumento: Instrumento): Observable<any> {
    return this.http.post<any>('http://localhost:8080/editInstrumentos/' + instrumento.id, instrumento);
  }

  desactivarInstrumento(id: number): Observable<any> {
    return this.http.post<any>('http://localhost:8080/instrumentos/activos/' + id, {});
  }

  getInstrumentoTipoVendedorId(tipo: number): Observable<Instrumento[]> {
    return this.http.get<Instrumento[]>('http://localhost:8080/instrumentos/instrumentosTipoUser?tipo_instrumento=' + tipo );
  }
}