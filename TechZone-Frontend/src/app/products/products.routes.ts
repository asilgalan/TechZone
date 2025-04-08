import { Routes } from '@angular/router';
import { LayoutproductComponent } from './layouts/layoutproduct/layoutproduct.component';
import { DetallesPageComponent } from './pages/detallesPage/detallesPage.component';

export const Productroutes: Routes = [
  {
    path:'',
    component:LayoutproductComponent,
    children:[
      {
        path:'detalleProducto',
        component:DetallesPageComponent
      }
    ]
  }
]
