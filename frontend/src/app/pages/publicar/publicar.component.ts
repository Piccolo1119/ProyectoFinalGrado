import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-publicar-instrumento',
  templateUrl: './publicar.component.html',
  styleUrls: ['./publicar.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule],
})
export class PublicarComponent {
  nombre: string | undefined;
  marca: string | undefined;
  precio: number | undefined;
  tipoInstrumento: string | undefined;
  imagenURL: string | undefined;
  idVendedor: number | undefined;
  descripcion: string | undefined; 
  imagen: Blob | undefined;
  imagenPreview: string | undefined;
  mensajeExito: string | undefined;
  mensajeError: string | undefined;

  constructor(private http: HttpClient, private router: Router) { }

  publicarInstrumento() {
    console.log('tipoInstrumento:', this.tipoInstrumento); // Verificar el valor de tipoInstrumento

    let tipoInstrumentoId: number;
  let tipoInstrumentoNombre: string; // Define la variable tipoInstrumentoNombre

  if (this.tipoInstrumento !== undefined) {
    tipoInstrumentoId = parseInt(this.tipoInstrumento);
    tipoInstrumentoNombre = (tipoInstrumentoId === 1) ? 'Guitarra' : 'Bajo'; // Asigna el nombre del tipo de instrumento según el id seleccionado
  } else {
    tipoInstrumentoId = 0; // O asigna cualquier otro valor predeterminado según sea necesario
    tipoInstrumentoNombre = ''; // O asigna cualquier otro valor predeterminado según sea necesario
  }

  const instrumento = {
    nombre: this.nombre,
    marca: this.marca,
    precio: this.precio,
    tipoInstrumento: {
      id: tipoInstrumentoId,
      nombre: tipoInstrumentoNombre
    },
    imagen: this.imagenURL,
    idVendedor: this.idVendedor,
    descripcion: this.descripcion
  };

    this.http.post<any>('http://localhost:8080/addInstrumentos', instrumento)
      .subscribe(response => {
        if (response) {
          console.log(response);
          this.mensajeExito = 'Instrumento ingresado con éxito.';
        } else {
          alert('Ha ocurrido un error al procesar la solicitud.');
        }
      }, error => {
        console.error('Ha ocurrido un error en la solicitud HTTP:', error);
        this.mensajeError = 'Error al publicar el instrumento. Rellena correctamente los campos';
      });
  }

  volverAHome() {
    this.router.navigate(['/']);
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    const reader = new FileReader();
    
    reader.onload = () => {
      this.imagenURL = reader.result as string; // Asignar la URL de la imagen a this.imagenURL
      this.imagenPreview = this.imagenURL; // Actualizar la vista previa de la imagen
    };
    
    reader.readAsDataURL(file);
  }
}
