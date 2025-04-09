import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';
import { CarritoSidebarComponent } from '../../../products/components/carrito-sidebar/carrito-sidebar.component';
import { ProductoFavoritoComponent } from '../../../products/components/producto-favorito/producto-favorito.component';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../auth/services/authService.service';
import { MenuMiCuentaComponent } from '../../../micuenta/components/menuMiCuenta/menuMiCuenta.component';
import { LoginPageComponent } from "../../../auth/pages/Login-page/Login-page.component";

@Component({
  selector: 'nav-bar',
  imports: [CarritoSidebarComponent, ProductoFavoritoComponent, RouterLink, CommonModule, MenuMiCuentaComponent, LoginPageComponent],
  templateUrl: './navBar.component.html',
  styleUrl:'./navBar.component.css',
})
export class NavBarComponent {

  authService=inject(AuthService);
  verMenuMiCuenta=signal(false);
  cartOpen = false;
  favoritesOpen=false;
  cartItemCount=1;
  inicioSesion=signal(false);
  verUserMenu(){
    this.verMenuMiCuenta.set(true);


  }

haslogin():boolean{
  setTimeout(()=>{
    return this.inicioSesion.set(true);
  },3000)

  return this.inicioSesion();

}




  openCart(): void {
    this.cartOpen = true;
  }
  openFavorites():void{
  this.favoritesOpen=true
  console.log(this.favoritesOpen)
   }

   // En tu componente
showCategories = false;
showMobileCategories = false;
activeSubcategory: number | null = null;
activeMobileSubcategory: number | null = null;

categories = [
  {
    id: 1,
    name: 'Electrónica',
    slug: 'electronica',
    subcategories: [
      { id: 101, name: 'Smartphones', slug: 'smartphones' },
      { id: 102, name: 'Laptops', slug: 'laptops' },
      { id: 103, name: 'Tablets', slug: 'tablets' }
    ]
  },
  {
    id: 2,
    name: 'Hogar',
    slug: 'hogar',
    subcategories: [
      { id: 201, name: 'Electrodomésticos', slug: 'electrodomesticos' },
      { id: 202, name: 'Muebles', slug: 'muebles' }
    ]
  },
  // Más categorías...
];

toggleCategories() {
  this.showCategories = !this.showCategories;
  if (!this.showCategories) {
    this.activeSubcategory = null;
  }
}

toggleMobileCategories() {
  this.showMobileCategories = !this.showMobileCategories;
  if (!this.showMobileCategories) {
    this.activeMobileSubcategory = null;
  }
}

toggleSubcategories(categoryId: number) {
  this.activeSubcategory = this.activeSubcategory === categoryId ? null : categoryId;
}

toggleMobileSubcategories(categoryId: number) {
  this.activeMobileSubcategory = this.activeMobileSubcategory === categoryId ? null : categoryId;
}


 }
