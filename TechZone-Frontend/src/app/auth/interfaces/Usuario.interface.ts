export interface User {
  idUsuario:           number;
  nombre:              string;
  apellidos:           string;
  email:               string;
  usuario:             string;
  telefono:            null;
  password:            string;
  ipRegistro:          null;
  fechaNacimiento:     null;
  fechaRegistro:       Date;
  ultimaActualizacion: Date;
  politica:            boolean;
  aceptaMarketing:     boolean;
  roles:               string[];
  direcciones:         null;
}
