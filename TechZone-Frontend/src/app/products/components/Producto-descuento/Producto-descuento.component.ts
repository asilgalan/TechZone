import { ChangeDetectionStrategy, Component, Input } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { CarouselModule } from 'primeng/carousel';
import { Producto } from '../../interfaces/Producto.interface';
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

  items:Producto[] = [
    {
      id: 1,
      name: "Batman",
      price: 99.99,
      image: "dc-batman.jpg",
      category: "Comics",
      isFavorite:false
    },
    {
      id: 2,
      name: "Superman",
      price: 89.99,
      image: "dc-superman.jpg",
      category: "Comics",
      isFavorite:false
    },
    {
      id: 3,
      name: "Spiderman",
      price: 79.99,
      image: "marvel-spider.jpg",
      category: "Comics",
      isFavorite:false
    },
    {
      id: 4,
      name: "Iron Man",
      price: 109.99,
      image: "marvel-iron.jpg",
      category: "Comics",
      isFavorite:false
    },
    {
      id: 5,
      name: "Wonder Woman",
      price: 94.99,
      image: "dc-wonderwoman.jpg",
      category: "Comics",
      isFavorite:false
    },
    {
      id: 6,
      name: "Black Panther",
      price: 84.99,
      image: "marvel-blackpanther.jpg",
      category: "Comics",
      isFavorite:false
    },
    {
      id: 7,
      name: "Flash",
      price: 74.99,
      image: "dc-flash.jpg",
      category: "Comics",
      isFavorite:false
    },
    {
      id: 8,
      name: "Thor",
      price: 99.99,
      image: "marvel-thor.jpg",
      category: "Comics",
      isFavorite:false
    },
    {
      id: 9,
      name: "Aquaman",
      price: 89.99,
      image: "dc-aquaman.jpg",
      category: "Comics",
      isFavorite:false
    },
    {
      id: 10,
      name: "Hulk",
      price: 104.99,
      image: "marvel-hulk.jpg",
      category: "Comics",
      isFavorite:false
    }
  ];




}
