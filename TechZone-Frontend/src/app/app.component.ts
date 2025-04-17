import { Carrito } from './products/interfaces/Producto.interface';
import { AuthService } from './auth/services/authService.service';
import { Component, inject, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ProductoService } from './products/services/Producto.service';
import { CarritoService } from './products/services/Carrito.service';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit  {


  auth=inject(AuthService)
  producto=inject(ProductoService)
 Carrito=inject(CarritoService)
  ngOnInit(): void {

    if(this.auth.user()?.idUsuario){
      this.producto.favoritosUsuario(this.auth.user()?.idUsuario!).subscribe();
      this.Carrito.obtenerCarrito(this.auth.user()?.idUsuario!).subscribe();
    }


  }


}
