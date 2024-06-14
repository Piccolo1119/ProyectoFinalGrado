import { Component, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { LoginService } from '../../services/auth/login.service';
import { LoginRequest } from '../../services/auth/loginRequest';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginError: string = '';
  loginForm = this.formBuilder.group({
    username:['', [Validators.required]],
    pass:['', [Validators.required]],
  })
  constructor(private formBuilder: FormBuilder, private router: Router, private loginService: LoginService) { }

  get email() {
    return this.loginForm.controls.username;
  }

  get password() {
    return this.loginForm.controls.pass;
  }

  login() {
    if(this.loginForm.valid) {
      this.loginService.login(this.loginForm.value as LoginRequest).subscribe({
        next: (userData) => {
          console.log(userData);
        },
        error: (errorData) => {
          console.log(errorData);
          this.loginError = "errorData";
        },
        complete: () => {
          console.log("login complete");
          this.router.navigateByUrl('/profile');
          this.loginForm.reset();
        }

      });
    } else {
      this.loginForm.markAllAsTouched();
      /* alert("Error al ingresasr "); */
    }
  }

}
