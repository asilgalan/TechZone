import { AuthService } from './../../../auth/services/authService.service';
import { Productos } from './../../interfaces/Producto.interface';
import { ChangeDetectionStrategy, Component, inject, OnInit, signal } from '@angular/core';
import { ProductoService } from '../../services/Producto.service';

import { map, tap } from 'rxjs';
import { CurrencyPipe } from '@angular/common';
import { RouterLink } from '@angular/router';

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



    ngOnInit(): void {
      this.obtenerProductos();
      }

    obtenerProductos(){

      this.productoService.productos().pipe(
        tap(resp => this.producto.set(resp))
      ).subscribe();

    }

    addFavorito(idProducto: number, idUsuario: number): void {
      if (!idUsuario) return;

      this.productoService.toggleFavorito(idProducto, idUsuario).subscribe({
        error: (err) => {
          console.error('Error al alternar favorito:', err);
          // Opcional: Mostrar mensaje de error
        }
      });
    }

}
