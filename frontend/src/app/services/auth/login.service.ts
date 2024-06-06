import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable, Inject } from '@angular/core';
import { LoginRequest } from './loginRequest';
import { Observable, catchError, throwError, BehaviorSubject, tap, map } from 'rxjs';
import { User } from './user';
import { isPlatformBrowser } from '@angular/common';
import { PLATFORM_ID } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  currentUserLoginOn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  currentUserData: BehaviorSubject<string> = new BehaviorSubject<string>("");

  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {
    if (isPlatformBrowser(this.platformId)) {
      const token = sessionStorage.getItem("token");
      this.currentUserLoginOn = new BehaviorSubject<boolean>(token != null);
      this.currentUserData = new BehaviorSubject<string>(token || "");
    }
  }

  login(credentials: LoginRequest): Observable<any> {
    return this.http.post<any>('http://localhost:8080/auth/login', credentials).pipe(
      tap((userData) => {
        if (isPlatformBrowser(this.platformId)) {
          sessionStorage.setItem("token", userData.token);
        }
        this.currentUserData.next(userData.token);
        this.currentUserLoginOn.next(true);
      }),
      map((userData) => userData.token),
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.log('Se ha producido un error', error.error);
    } else {
      console.log('Backend retornó el código de estado', error);
    }
    return throwError(() => new Error('Algo ha ido mal; por favor, intente de nuevo más tarde.'));
  }

  logout(): void {
    if (isPlatformBrowser(this.platformId)) {
      sessionStorage.removeItem("token");
    }
    this.currentUserLoginOn.next(false);
  }

  get userData(): Observable<string> {
    return this.currentUserData.asObservable();
  }

  getUserData(): Observable<boolean> {
    return this.currentUserLoginOn.asObservable();
  }

}
