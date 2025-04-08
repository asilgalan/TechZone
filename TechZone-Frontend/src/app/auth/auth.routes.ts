
import { Routes } from '@angular/router';
import { LayoutsAuthComponent } from './layouts/LayoutsAuth/LayoutsAuth.component';
import { LoginPageComponent } from './pages/Login-page/Login-page.component';
import { RegistroPageComponent } from './pages/Registro-page/Registro-page.component';
;

export const routesAuth: Routes = [

  {
    path:'',
    component:LayoutsAuthComponent,
    children:[
      {
        path:"login",
        component:LoginPageComponent

      },
      {
        path:"registro",
        component:RegistroPageComponent
      }

    ]
  }

]
