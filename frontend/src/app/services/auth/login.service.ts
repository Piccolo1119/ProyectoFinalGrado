import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginRequest } from './loginRequest';
import { Observable, catchError, throwError, BehaviorSubject, tap} from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  currentUserLoginOn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  currentUserData: BehaviorSubject<User> = new BehaviorSubject<User>({id:0, email:''});

  constructor(private http: HttpClient) { }

    login(credentials:LoginRequest):Observable<User>{
      return this.http.get<User>('../../../assets/data.json').pipe(
        tap( (userData: User) => {
          this.currentUserData.next(userData);
          this.currentUserLoginOn.next(true);
        }),
        catchError(this.handleError)
      );
  } 

  private handleError(error:HttpErrorResponse){
    if(error.status === 0) {
      console.log('Se ha producido un error', error.error);
    }
    else {
      console.log('Backend retornó el código de estado', error.status, error.error);
    }
    return throwError(() => new Error('Algo ha ido mal; por favor, inténtelo de nuevo mas tarde.'));
  }

  getUserData():Observable<Boolean>{
    return this.currentUserLoginOn.asObservable();
  }

}
  
