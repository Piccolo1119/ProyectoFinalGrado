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

  getInstrumentosByVendedorId(vendedorId: number): Observable<Instrumento[]> {
    return this.http.get<Instrumento[]>('http://localhost:8080/instrumentos/vendedorId/' + vendedorId);
  }
}