import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

import { GalleriaModule } from 'primeng/galleria';

@Component({
  selector: 'app-detalles-page',
  imports: [GalleriaModule,CommonModule],
  templateUrl: './detallesPage.component.html'
})
export class DetallesPageComponent {

 }
