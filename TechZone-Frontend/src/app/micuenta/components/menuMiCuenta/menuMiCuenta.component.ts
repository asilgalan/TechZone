import { AuthService } from './../../../auth/services/authService.service';
import { ChangeDetectionStrategy, Component, EventEmitter, inject, Input, Output,  } from '@angular/core';

@Component({
  selector: 'app-menu-mi-cuenta',
  imports: [],
  templateUrl: './menuMiCuenta.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class MenuMiCuentaComponent {
  authService=inject(AuthService)

  @Input() verMenuMiCuenta = false;
  @Output() closeMenu = new EventEmitter<void>();

  logout() {
    this.authService.logout();
    this.closeMenu.emit();
  }

}
