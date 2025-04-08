import { Routes } from '@angular/router';
import { NotFoundPageComponent } from './shared/pages/notFoundPage/notFoundPage.component';


export const routes: Routes = [

  {
    path: '',
    loadChildren: () => import('./TechZone-Front/techzone.routes').then(m => m.TechZoneRoutes)

  },
  {
    path:'auth',
    loadChildren:() => import('./auth/auth.routes').then((auth) =>auth.routesAuth)
  },
  {
    path:'producto',
    loadChildren:() => import('./products/products.routes').then((product)=> product.Productroutes)

  },

  {
    path:'**',
    component:NotFoundPageComponent
  }


];
