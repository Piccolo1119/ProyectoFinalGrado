import { Component, OnDestroy, OnInit } from '@angular/core';
import { NavComponent } from '../../components/nav/nav.component';
import { CommonModule } from '@angular/common';
import { LoginService } from '../../services/auth/login.service';
import { User } from '../../services/auth/user';
import {PersonalDetailsComponent} from '../../components/personal-details/personal-details.component';
import { InstrumentosService } from '../../services/instrumentos/instrumentos.service';
import { Instrumento } from '../../../model/instrumento.model';
import { Subscription } from 'rxjs';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Pedidos } from '../../../model/pedidos.model';
import { PedidosService } from '../../services/pedidos/pedidos.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    NavComponent,
    CommonModule,
    PersonalDetailsComponent,
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit, OnDestroy {
  userLoginOn: boolean = false;
  userData?: User;
  instrumentos: Instrumento[] = [];
  pedidos: Pedidos[] = [];
  private subscriptions: Subscription[] = [];
  deleteSuccessMessage: string | undefined;
  editMode: boolean = false;
  instrumentToEdit: Instrumento | null = null;
  editForm: FormGroup;

  constructor(
    private loginService: LoginService,
    private instrumentosService: InstrumentosService,
    private pedidosService: PedidosService,
    private formBuilder: FormBuilder
  ) { 
    this.editForm = this.formBuilder.group({
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      marca: ['', Validators.required],
      precio: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.subscriptions.push(
      this.loginService.currentUserLoginOn.subscribe({
        next: (userLoginOn) => {
          this.userLoginOn = userLoginOn;
          if (this.userLoginOn) {
            this.loadInstruments();
            this.loadPedidos();
          }
        }
      })
    );
  }

  loadPedidos(): void {
    const userId = 1; // Reemplaza esto con el ID real del usuario autenticado
    console.log('ID del usuario:', userId); // Log del ID del usuario
    this.subscriptions.push(
      this.pedidosService.getPedidosByComprador(userId).subscribe({
        next: (pedidos) => {
          this.pedidos = pedidos;
          console.log('Pedidos devueltos:', pedidos); // Log de los instrumentos devueltos
        },
        error: (err) => {
          console.error('Error fetching pedidos:', err);
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

  deleteInstrumento(id: number): void {
    this.instrumentos = this.instrumentos.filter(instrumento =>  instrumento.id !== id
    );

    this.subscriptions.push(
      this.instrumentosService.deleteInstrumento(id).subscribe({
        next: () => {
          console.log('Instrumento eliminado con éxito');
          this.deleteSuccessMessage = 'El instrumento ha sido eliminado correctamente.';
          setTimeout(() => {
            this.deleteSuccessMessage = '';
          }, 3000);
        },
        error: (err) => {
          console.error('Error al eliminar instrumento:', err);
          this.loadInstruments();
        }
      })
    );
  }

  editInstrument(instrument: Instrumento): void {
    this.instrumentToEdit = instrument;
    this.editForm.patchValue({
      nombre: instrument.nombre,
      descripcion: instrument.descripcion,
      marca: instrument.marca,
      precio: instrument.precio
    });
    this.editMode = true;
  }

  saveInstrument(): void {
    if (this.editForm.valid && this.instrumentToEdit) {
      const editedInstrument: Instrumento = {
        ...this.instrumentToEdit,
        nombre: this.editForm.value.nombre,
        descripcion: this.editForm.value.descripcion,
        marca: this.editForm.value.marca,
        precio: this.editForm.value.precio
      };

      this.subscriptions.push(
        this.instrumentosService.editInstrumento(editedInstrument).subscribe({
          next: () => {
            console.log('Instrumento editado con éxito');
            this.deleteSuccessMessage = 'El instrumento ha sido editado correctamente.';
            setTimeout(() => {
              this.deleteSuccessMessage = '';
            }, 3000);
            this.editForm.reset();
            this.instrumentToEdit = null;
            this.editMode = false;
            this.loadInstruments();
          },
          error: (err) => {
            console.error('Error al editar instrumento:', err);
            this.loadInstruments();
          }
        })
      );
    }
  }

  cancelEdit(): void {
    this.editForm.reset();
    this.instrumentToEdit = null;
    this.editMode = false;
  }
}
