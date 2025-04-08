import { ChangeDetectionStrategy, Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { NavBarComponent } from "../../../shared/components/navBar/navBar.component";
import { FooterHomeComponent } from '../../../shared/components/footer-home/footer-home.component';


@Component({
  selector: 'app-techzone-layout',
  imports: [RouterOutlet, NavBarComponent,FooterHomeComponent],
  templateUrl: './TechzoneLayout.component.html'
})
export class TechzoneLayoutComponent { }
