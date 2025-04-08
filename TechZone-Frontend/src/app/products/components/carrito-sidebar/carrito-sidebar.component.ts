import { CommonModule, CurrencyPipe } from '@angular/common';
import { ChangeDetectionStrategy, Component, EventEmitter, Input, Output } from '@angular/core';

interface CartItem {
  id: number;
  name: string;
  price: number;
  quantity: number;
  color: string;
  image: string;
}

@Component({
  selector: 'app-carrito-sidebar',
  imports: [CurrencyPipe,CommonModule],
  templateUrl: './carrito-sidebar.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CarritoSidebarComponent {

  @Input() isOpen = false;
  @Output() closeCart = new EventEmitter<void>();

  cartItems: CartItem[] = [
    {
      id: 1,
      name: 'Throwback Hip Bag',
      price: 90.00,
      quantity: 1,
      color: 'Salmon',
      image: 'https://tailwindcss.com/plus-assets/img/ecommerce-images/shopping-cart-page-04-product-01.jpg'
    },
    {
      id: 2,
      name: 'Medium Stuff Satchel',
      price: 32.00,
      quantity: 1,
      color: 'Blue',
      image: 'https://tailwindcss.com/plus-assets/img/ecommerce-images/shopping-cart-page-04-product-02.jpg'
    }
  ];
  @Input() cantidaddeProductos=this.cartItems.length;

  get subtotal(): number {
    return this.cartItems.reduce((sum, item) => sum + (item.price * item.quantity), 0);
  }

  totalPorProducto(id: number): number {
    return this.cartItems
      .filter(e => e.id === id)
      .reduce((total, item) => total + (item.price * item.quantity), 0);
  }

  increaseQuantity(item: CartItem): void {
    item.quantity++;
  }

  decreaseQuantity(item: CartItem): void {
    if (item.quantity > 1) {
      item.quantity--;
    }
  }

  removeItem(itemToRemove: CartItem): void {
    this.cartItems = this.cartItems.filter(item => item.id !== itemToRemove.id);
  }
}
