export interface Instrumento {
    id: number;
    nombre: string;
    marca: string;
    precio: number;
    tipoInstrumento: number; // Ajusta esto según tu modelo de tipoInstrumento
    imagen: string;
    idVendedor: number;
    descripcion: string;
  }