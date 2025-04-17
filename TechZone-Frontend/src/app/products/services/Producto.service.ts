import { AuthService } from './../../auth/services/authService.service';
import { Productos } from './../interfaces/Producto.interface';
import { inject, Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({providedIn: 'root'})
export class ProductoService {
  private http = inject(HttpClient);

  private _favoritos = signal<any[]>([]);
  public favoritos = this._favoritos.asReadonly();
     authService=inject(AuthService)
  productos(): Observable<Productos[]> {
    return this.http.get<Productos[]>(`${environment.base_url}/productos/todos`).pipe(
      tap(resp => console.log(resp))
    );
  }

  addFavorito(idUsuario: number, idProducto: number): Observable<any> {
    const body = {
      idUsuario: idUsuario,
      idProducto: idProducto
    };

    return this.http.post<any>(`${environment.base_url}/productos/guardarFavoritos`, body).pipe(
      tap(() => {

        this.favoritosUsuario(idUsuario).subscribe();
      }),
      catchError(error => {
        console.error('Error en la solicitud:', error);
        return throwError(() => new Error('Error al guardar favorito'));
      })
    );
  }

  favoritosUsuario(id: number): Observable<any[]> {
    return this.http.get<any[]>(`${environment.base_url}/productos/favoritoUsuario/${id}`).pipe(
      tap(resp => {
        console.log(resp);

        this._favoritos.set(resp);
      }),
      catchError(error => {
        console.error("Error en la solicitud", error);
        return throwError(() => new Error('Error al solicitar favorito de cada usuario'));
      })
    );
  }

  eliminarFavorito(id: number): Observable<any> {
    return this.http.get(`${environment.base_url}/productos/eliminarFavorito/${id}`).pipe(

      tap(() => this.favoritosUsuario(this.authService.user()?.idUsuario!).subscribe()),
      catchError(error => {
        console.error("Error en la solicitud", error);
        return throwError(() => new Error('Error al eliminar favorito'));
      })
    );
  }


toggleFavorito(idProducto: number, idUsuario: number): Observable<any> {
  const favoritoExistente = this._favoritos().find(f => f.producto.idProducto === idProducto);

  if (favoritoExistente) {
    return this.eliminarFavorito(favoritoExistente.idFavorito).pipe(
      tap(() => this._favoritos.update(favs =>
        favs.filter(f => f.producto.idProducto !== idProducto)
      )
    ));
  } else {
    return this.addFavorito(idUsuario, idProducto).pipe(
      tap((nuevoFavorito) => {
        this._favoritos.update(favs => [...favs, nuevoFavorito]);
      })
    );
  }
}



  esFavorito(idProducto: number): boolean {
    return this._favoritos().some(f => f.producto.idProducto === idProducto);
  }
}
