import { ChangeDetectionStrategy, Component } from '@angular/core';
import { ProductoRelevantesComponent } from '../../../products/components/Producto-Relevantes/Producto-Relevantes.component';
import { ProductoCategoriaComponent } from '../../../products/components/producto-categoria/producto-categoria.component';
import { ProductoDescuentoComponent } from "../../../products/components/Producto-descuento/Producto-descuento.component";
import { CategoriaRelevanteComponent } from "../../../products/components/categoriaRelevante/categoriaRelevante.component";
import { PromoNavbarComponent } from '../../components/Promo_navbar/Promo_navbar.component';
import { IncentivoComponent } from "../../components/Incentivo/Incentivo.component";
import { ProductoVisitadoComponent } from "../../../products/components/producto-visitados/producto-visitados.component";
import { MarcasComponent } from "../../components/Marcas/Marcas.component";

@Component({
  selector: 'app-home-pages',
  imports: [PromoNavbarComponent, ProductoRelevantesComponent, ProductoCategoriaComponent, ProductoDescuentoComponent, CategoriaRelevanteComponent, IncentivoComponent, ProductoVisitadoComponent, MarcasComponent],
  templateUrl: './HomePages.component.html'
})
export class HomePagesComponent { }
