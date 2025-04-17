import { computed, inject, Injectable, signal } from '@angular/core';
import { catchError, Observable, tap } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Carrito, ItemCarrito } from '../interfaces/Producto.interface';
import { HttpClient } from '@angular/common/http';
import { toObservable } from '@angular/core/rxjs-interop';

@Injectable({providedIn: 'root'})
export class CarritoService {
  private http = inject(HttpClient);
  private _carrito = signal<Carrito | null>(null);
  public carrito = this._carrito.asReadonly();

  public carrito$ = toObservable(this._carrito);


  initCarrito(usuarioId: number): void {
    if (!this._carrito()) {
      this.obtenerCarrito(usuarioId).subscribe();
    }
  }

  obtenerCarrito(usuarioId: number): Observable<Carrito> {
    return this.http.get<Carrito>(`${environment.base_url}/carrito/usuario-carrito/${usuarioId}`).pipe(
      tap(response => console.log('Respuesta al agregar:', response)),
      tap(carrito => {
        this._carrito.set(carrito);
      })
    );
  }

  agregarAlCarrito(usuarioId: number, productoId: number, cantidad: number = 1): Observable<ItemCarrito> {
    return this.http.post<ItemCarrito>(
      `${environment.base_url}/carrito/agregar`,
      null,
      { params: { usuarioId, productoId, cantidad } }
    ).pipe(
      tap(response => console.log('Respuesta al agregar:', response)),
      tap(() => this.obtenerCarrito(usuarioId).subscribe()),

    );
  }

  eliminarDelCarrito(usuarioId: number, productoId: number): Observable<void> {
    return this.http.delete<void>(
      `${environment.base_url}/carrito/eliminar`,
      { params: { usuarioId, productoId } }
    ).pipe(
      tap(() => this.obtenerCarrito(usuarioId).subscribe())
    );
  }

  actualizarCantidad(usuarioId: number, productoId: number, nuevaCantidad: number): Observable<ItemCarrito> {
    return this.http.put<ItemCarrito>(
      `${environment.base_url}/carrito/actualizar-cantidad`,
      null,
      { params: { usuarioId, productoId, nuevaCantidad } }
    ).pipe(
      tap(() => this.obtenerCarrito(usuarioId).subscribe())
    );
  }

  vaciarCarrito(usuarioId: number): Observable<void> {
    return this.http.delete<void>(
      `${environment.base_url}/carrito/vaciar/${usuarioId}`
    ).pipe(
      tap(() => this._carrito.set(null))
    );
  }

  calcularTotal(carrito: Carrito): number {
    return carrito.items.reduce(
      (total, item) => total + (item.producto.precio * item.cantidad),
      0
    );
  }

  clearLocalCart(): void {
    this._carrito.set(null);
  }
}
