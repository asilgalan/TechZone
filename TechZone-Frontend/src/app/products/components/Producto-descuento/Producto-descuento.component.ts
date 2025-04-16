import { ChangeDetectionStrategy, Component, Input } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { CarouselModule } from 'primeng/carousel';
import { Productos } from '../../interfaces/Producto.interface';
import { CurrencyPipe } from '@angular/common';

interface FavoriteItem {
  id: number;
  name: string;
  price: number;
  image: string;
  category: string;
}
@Component({
  selector: 'app-producto-descuento',
  imports: [CarouselModule, ButtonModule,CurrencyPipe],
  templateUrl: './Producto-descuento.component.html'
})
export class ProductoDescuentoComponent {






}
