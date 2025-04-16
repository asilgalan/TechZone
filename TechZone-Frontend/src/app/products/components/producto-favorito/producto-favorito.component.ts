import {  CurrencyPipe } from '@angular/common';
import { ChangeDetectionStrategy, Component, computed, effect, EventEmitter, inject, input, Input, OnInit, Output, signal } from '@angular/core';
import { ProductoService } from '../../services/Producto.service';
import { AuthService } from '../../../auth/services/authService.service';
import { catchError, tap } from 'rxjs';


@Component({
  selector: 'producto-favorito',
  standalone:true,
  imports: [CurrencyPipe],
  templateUrl: './producto-favorito.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProductoFavoritoComponent implements OnInit {


  @Input() isOpen = false;
  @Output() closeFavorites = new EventEmitter<void>();
  productoService=inject(ProductoService);
  favoritos=signal<any[]>([]);
   authService=inject(AuthService);

   userId = computed(() => this.authService.user()?.idUsuario||null);

ngOnInit(): void {

  this.cargarFavoritos();
}



  cargarFavoritos(){
    const id = this.userId();
  if (!this.userId()) {
    console.warn('No hay usuario autenticado');
    this.favoritos.set([]);
    return;
  }

    this.productoService.favoritosUsuario(this.userId()!).pipe(
      tap((resp) => this.favoritos.set(resp)),

    ).subscribe();


  }

  eliminarFavorito(id:number): void {
    this.productoService.eliminarFavorito(id).pipe(

      tap(() =>{

        this.favoritos.update(favoritos =>
          favoritos.filter(fav => fav.idFavorito !== id)
        );
      })

    )

    .subscribe();

  }

  moveToCart(): void {

  }
}
