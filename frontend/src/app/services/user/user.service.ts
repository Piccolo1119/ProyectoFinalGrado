import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { User } from '../auth/user';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUser(id:number):Observable<User>
  {
    return this.http.get<User>("http://localhost:8080/api/v1/usuarios/"+1).pipe(
      catchError(this.handleError)
    )
  }

  private handleError(error:HttpErrorResponse){
    if(error.status===0){
      console.error('Se ha producido un error ', error.error);
    }
    else {
      console.error('Backend retornó el código de estado ', error.status, error.error);
    }
    return throwError(() => new Error('Algo ha ido mal; por favor, inténtelo de nuevo mas tarde.'));
  }

  updateUser(usuariosRequest:User):Observable<any>
  {
    return this.http.put<User>("http://localhost:8080/api/v1/usuarios/", usuariosRequest).pipe(
      catchError(this.handleError)
    )
  }
}
