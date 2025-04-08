import { Injectable } from '@angular/core';
import { Producto } from '../interfaces/Producto.interface';

@Injectable({providedIn: 'root'})
export class ProductoService {
  private favorites: Producto[] = [];

  addToFavorites(product: Producto): void {
    if (!this.isFavorite(product.id)) {
      product.isFavorite = true;
      this.favorites.push(product);
    }
  }

  removeFromFavorites(productId: number): void {
    this.favorites = this.favorites.filter(item => item.id !== productId);
  }

  getFavorites(): Producto[] {
    return this.favorites;
  }

  isFavorite(productId: number): boolean {
    return this.favorites.some(item => item.id === productId);
  }

}
