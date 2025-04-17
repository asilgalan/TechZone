import { CommonModule, CurrencyPipe } from '@angular/common';
import { ChangeDetectionStrategy, Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { AuthService } from '../../../auth/services/authService.service';
import { CarritoService } from '../../services/Carrito.service';
import { toSignal } from '@angular/core/rxjs-interop';
import { of } from 'rxjs';



@Component({
  selector: 'app-carrito-sidebar',
  imports: [CurrencyPipe,CommonModule],
  templateUrl: './carrito-sidebar.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CarritoSidebarComponent {

  @Input() isOpen = false;
  @Output() closeCart = new EventEmitter<void>();

  private carritoService = inject(CarritoService);
  private authService = inject(AuthService);

  carrito = toSignal(this.carritoService.carrito$);

  @Input() cantidaddeProductos=this.carrito()?.items.length;
  calcularTotal(): number {
    const currentCart = this.carrito();
    return currentCart ? this.carritoService.calcularTotal(currentCart) : 0;
  }

  actualizarCantidad(productoId: number, nuevaCantidad: number): void {
    const usuarioId = this.authService.user()?.idUsuario;
    if (!usuarioId || nuevaCantidad < 1) return;

    if(nuevaCantidad<=5){
      this.carritoService.actualizarCantidad(usuarioId, productoId, nuevaCantidad)
      .subscribe({
        error: (err) => console.error('Error al actualizar cantidad:', err)
      });
    }else{
      return;
    }

  }

  eliminarProducto(productoId: number): void {
    const usuarioId = this.authService.user()?.idUsuario;
    if (!usuarioId) return;

    this.carritoService.eliminarDelCarrito(usuarioId, productoId)
      .subscribe({
        error: (err) => console.error('Error al eliminar producto:', err)
      });
  }

  vaciarCarrito(): void {
    const usuarioId = this.authService.user()?.idUsuario;
    if (!usuarioId) return;

    this.carritoService.vaciarCarrito(usuarioId)
      .subscribe({
        next: () => this.closeCart.emit(),
        error: (err) => console.error('Error al vaciar carrito:', err)
      });
  }

}
