import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegisterRequest } from './registerRequest';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }

  register(credentials: RegisterRequest): Observable<any> {
    return this.http.post<any>("http://localhost:8080/auth/register", credentials);
  }
}
