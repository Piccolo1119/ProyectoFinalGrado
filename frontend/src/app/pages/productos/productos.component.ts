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
    { name: 'Producto 3', description: 'Descripción del producto 3', price: 300, image: 'path/to/image3.jpg' },
    { name: 'Producto 4', description: 'Descripción del producto 4', price: 400, image: 'path/to/image4.jpg' },
    { name: 'Producto 5', description: 'Descripción del producto 5', price: 500, image: 'path/to/image5.jpg' },
    { name: 'Producto 6', description: 'Descripción del producto 6', price: 600, image: 'path/to/image6.jpg' },
    { name: 'Producto 7', description: 'Descripción del producto 7', price: 700, image: 'path/to/image7.jpg' },
    { name: 'Producto 8', description: 'Descripción del producto 8', price: 800, image: 'path/to/image8.jpg' },
    { name: 'Producto 9', description: 'Descripción del producto 9', price: 900, image: 'path/to/image9.jpg' },
    { name: 'Producto 10', description: 'Descripción del producto 10', price: 1000, image: 'path/to/image10.jpg' },
    { name: 'Producto 11', description: 'Descripción del producto 11', price: 1100, image: 'path/to/image11.jpg' },
    { name: 'Producto 12', description: 'Descripción del producto 12', price: 1200, image: 'path/to/image12.jpg' },
    { name: 'Producto 13', description: 'Descripción del producto 13', price: 1300, image: 'path/to/image13.jpg' },
    { name: 'Producto 14', description: 'Descripción del producto 14', price: 1400, image: 'path/to/image14.jpg' },
    { name: 'Producto 15', description: 'Descripción del producto 15', price: 1500, image: 'path/to/image15.jpg' },
    { name: 'Producto 16', description: 'Descripción del producto 16', price: 1600, image: 'path/to/image16.jpg' },
    { name: 'Producto 17', description: 'Descripción del producto 17', price: 1700, image: 'path/to/image17.jpg' },
    { name: 'Producto 18', description: 'Descripción del producto 18', price: 1800, image: 'path/to/image18.jpg' },
    { name: 'Producto 19', description: 'Descripción del producto 19', price: 1900, image: 'path/to/image19.jpg' },
    { name: 'Producto 20', description: 'Descripción del producto 20', price: 2000, image: 'path/to/image20.jpg' }
  ];

  constructor() { }

  ngOnInit(): void {
  }
}
