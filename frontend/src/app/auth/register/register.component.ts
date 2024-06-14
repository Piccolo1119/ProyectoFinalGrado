import { Component, OnInit } from '@angular/core';
import { RegisterService } from '../../services/register/register.service';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { RegisterRequest } from '../../services/register/registerRequest';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  mensajeExito: String = '';
  registerError: String = '';
  registerForm = this.formBuilder.group({
    nombre: ['', Validators.required],
    apellidos: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    username: ['', Validators.required],
    pass: ['', Validators.required],
    telefono: ['', Validators.required],
    fechaNac: ['', Validators.required]
  });

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private registerService: RegisterService
  ) {}

  onSubmit() {
    if (this.registerForm.valid) {
      this.registerService.register(this.registerForm.value as unknown as RegisterRequest).subscribe({
        next: (userData) => {
          console.log(userData);
        },
        error: (errorData) => {
          console.log(errorData);
          this.registerError = errorData.error;
        },
        complete: () => {
          console.log("register complete");
          this.mensajeExito = 'Instrumento ingresado con éxito.';
          setTimeout(() => {
            this.router.navigateByUrl('/login');
            this.registerForm.reset();
          }, 3000);
        }
      });
    } else {
      this.registerForm.markAllAsTouched();
      alert("Error");
    }
  }
}