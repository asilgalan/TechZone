export interface Productos {
  idProducto:          number;
  nombre:              string;
  slug:                string;
  descripcionCorta:    string;
  descripcionLarga:    string;
  marca:               Marca;
  imagenPrincipal:      string;
  imagenes:            any[];
  categoria:           Categoria;
  sku:                 string;
  modelo:              string;
  precio:              number;
  coste:               number;
  iva:                 number;
  peso:                number;
  fechaLanzamiento:    Date;
  fechaCreacion:       Date;
  ultimaActualizacion: Date;
  garantia:            number;
  valoracion:          any[];
  nuevo:               boolean;
  oferta:              boolean;
}

export interface Categoria {
  idCategoria:   number;
  nombre:        string;
  slug:          string;
  padre:         null;
  descripcion:   string;
  imagenUrl:     string;
  icono:         string;
  nivel:         number;
  orden:         number;
  subcategorias: any[];
}

export interface Marca {
  idMarca:     number;
  nombre:      string;
  slug:        string;
  descripcion: string;
  logoImg:     string;
  enlaceWeb:   string;
}
