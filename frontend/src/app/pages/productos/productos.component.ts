import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule],
})
export class ProductosComponent implements OnInit {
  productos = [
    { name: 'Producto 1', description: 'Descripción del producto 1', price: 100, image: 'path/to/image1.jpg' },
    { name: 'Producto 2', description: 'Descripción del producto 2', price: 200, image: 'path/to/image2.jpg' },
    { name: 'Producto 3', description: 'Descripción del producto 3', price: 300, image: 'path/to/image3.jpg' }
  ];

  constructor() { }

  ngOnInit(): void {
  }
}
