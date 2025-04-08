import {  CurrencyPipe } from '@angular/common';
import { ChangeDetectionStrategy, Component, EventEmitter, input, Input, Output, signal } from '@angular/core';

interface FavoriteItem {
  id: number;
  name: string;
  price: number;
  image: string;
  category: string;
}
@Component({
  selector: 'producto-favorito',
  standalone:true,
  imports: [CurrencyPipe],
  templateUrl: './producto-favorito.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProductoFavoritoComponent {

  @Input() isOpen = false;
  @Output() closeFavorites = new EventEmitter<void>();



  favoriteItems: FavoriteItem[] = [
      {
        id: 1,
        name: 'Throwback Hip Bag',
        price: 90.00,
       category:"ropa",
        image: 'https://tailwindcss.com/plus-assets/img/ecommerce-images/shopping-cart-page-04-product-01.jpg'
      },
      {
        id: 2,
        name: 'Medium Stuff Satchel',
        price: 32.00,
        category:"ropa",
        image: 'https://tailwindcss.com/plus-assets/img/ecommerce-images/shopping-cart-page-04-product-02.jpg'
      }
    ];


  removeFavorite(item: FavoriteItem): void {
    this.favoriteItems = this.favoriteItems.filter(fav => fav.id !== item.id);
  }

  moveToCart(item: FavoriteItem): void {
    // Implementa lógica para mover a carrito
    this.removeFavorite(item);
  }
}
