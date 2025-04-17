import { AuthService } from './../../../auth/services/authService.service';
import { Productos } from './../../interfaces/Producto.interface';
import { ChangeDetectionStrategy, Component, inject, OnInit, signal } from '@angular/core';
import { ProductoService } from '../../services/Producto.service';

import { map, tap } from 'rxjs';
import { CurrencyPipe } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { CarritoService } from '../../services/Carrito.service';

@Component({
  selector: 'app-producto-relevantes',
  imports: [CurrencyPipe,RouterLink],
  templateUrl: './Producto-Relevantes.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProductoRelevantesComponent implements OnInit {

    productoService=inject(ProductoService);
    authService= inject(AuthService)
    producto=signal<Productos[]>([]);
    carritoService=inject(CarritoService)
 router=inject(Router)

 ngOnInit(): void {
  this.obtenerProductos();
}

obtenerProductos() {
  this.productoService.productos().pipe(
    tap(resp => this.producto.set(resp))
  ).subscribe();
}

addFavorito(idProducto: number, idUsuario: number): void {
  if (!idUsuario) return;

  this.productoService.toggleFavorito(idProducto, idUsuario).subscribe({
    error: (err) => {
      console.error('Error al alternar favorito:', err);

    }
  });
}

addCarrito(idProducto: number): void {
  if (!this.authService.user()) {
    this.router.navigateByUrl("/auth/login");
    return;
  }

  const usuarioId = this.authService.user()?.idUsuario!;
  this.carritoService.agregarAlCarrito(usuarioId, idProducto).subscribe();
}
}
