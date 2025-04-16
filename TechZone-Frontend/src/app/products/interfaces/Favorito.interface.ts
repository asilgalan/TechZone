export interface Favorito {
  producto:   Producto;
  idFavorito: number;
}

export interface Producto {
  idProducto:       number;
  nombre:           string;
  slug:             string;
  descripcionCorta: string;
  descripcionLarga: string;
  imagenPrincipal:  string;
  imagenes:         any[];
  categoria:        Categoria;
  sku:              string;
  modelo:           string;
  precio:           number;
  coste:            number;
  oferta:           boolean;
}

export interface Categoria {
  idCategoria: number;
  nombre:      string;
  slug:        string;
}
