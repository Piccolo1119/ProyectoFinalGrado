import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { User } from '../../services/auth/user';
import { UserService } from '../../services/user/user.service';
import { environment } from '../../../environments/environment';
import { FormBuilder, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginService } from '../../services/auth/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-personal-details',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './personal-details.component.html',
  styleUrl: './personal-details.component.css'
})
export class PersonalDetailsComponent {
  errorMessage: string = "";
  user?:User;
  userLoginOn: Boolean=false;
  editMode: Boolean=false;

  registerForm=this.formBuilder.group({
    id:[''],
    apellidos:['',Validators.required],
    nombre:['', Validators.required],
    email :['',Validators.required],
    telefono :['',Validators.required],
    fechaNac :['',Validators.required],

  })


  constructor(private userService:UserService, private formBuilder: FormBuilder, private LoginService:LoginService) {
    this.userService.getUser(1).subscribe({
      next: (userData) => {
        this.user = userData;
        this.registerForm.controls.id.setValue(userData.id.toString());
        this.registerForm.controls.nombre.setValue(userData.nombre);
        this.registerForm.controls.apellidos.setValue(userData.apellidos);
        this.registerForm.controls.email.setValue(userData.email);
        this.registerForm.controls.telefono.setValue(userData.telefono);
        const date = new Date(userData.fechaNac);
        const dateString = date.toISOString().split('T')[0]; // "YYYY-MM-DD"
        this.registerForm.controls.fechaNac.setValue(dateString);
      },
      error: (errorData) => {
        this.errorMessage = "Debe iniciar sesión para ingresar a la aplicación";
      },
      complete: () => {
        console.log("User data Ok");
      }
    })

    this.LoginService.userLoginOn.subscribe({
      next:(userLoginOn) => {
        this.userLoginOn=userLoginOn;
      }
    })
  }

  get nombre() {
    return this.registerForm.get('nombre');
  }

  get apellidos() {
    return this.registerForm.get('apellidos');
  }

  get email() {
    return this.registerForm.get('email');
  }

  get telefono() {
    return this.registerForm.get('telefono');
  }

  get fechaNac() {
    return this.registerForm.get('fechaNac');
  }


   savePersonalDetailsData()
  {
    if (this.registerForm.valid)
    {
      this.userService.updateUser(this.registerForm.value as unknown as User).subscribe({
        next:() => {
          this.editMode=false;
          this.user=this.registerForm.value as unknown as User;
        },
        error:(errorData)=> console.error(errorData)
      })
    }
  }
}
