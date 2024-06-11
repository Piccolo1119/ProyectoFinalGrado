import { Component, OnDestroy, OnInit } from '@angular/core';
import { NavComponent } from '../../components/nav/nav.component';
import { CommonModule } from '@angular/common';
import { LoginService } from '../../services/auth/login.service';
import { User } from '../../services/auth/user';
import {PersonalDetailsComponent} from '../../components/personal-details/personal-details.component';
import { InstrumentosService } from '../../services/instrumentos/instrumentos.service';
import { Instrumento } from '../../../model/instrumento.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    NavComponent,
    CommonModule,
    PersonalDetailsComponent
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit, OnDestroy {
  userLoginOn: boolean = false;
  userData?: User;
  instrumentos: Instrumento[] = [];
  private subscriptions: Subscription[] = [];

  constructor(
    private loginService: LoginService,
    private instrumentosService: InstrumentosService
  ) { }

  ngOnInit(): void {
    this.subscriptions.push(
      this.loginService.currentUserLoginOn.subscribe({
        next: (userLoginOn) => {
          this.userLoginOn = userLoginOn;
          if (this.userLoginOn) {
            this.loadInstruments();
          }
        }
      })
    );
  }

  loadInstruments(): void {
    const userId = 1; // Reemplaza esto con el ID real del usuario autenticado
    console.log('ID del usuario:', userId); // Log del ID del usuario
    this.subscriptions.push(
      this.instrumentosService.getInstrumentosByVendedorId(userId).subscribe({
        next: (instruments) => {
          this.instrumentos = instruments;
          console.log('Instrumentos devueltos:', instruments); // Log de los instrumentos devueltos
        },
        error: (err) => {
          console.error('Error fetching instruments:', err);
        }
      })
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

}
