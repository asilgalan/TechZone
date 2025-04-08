import { ChangeDetectionStrategy, Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-layouts-auth',
  imports: [RouterOutlet],
  templateUrl: './LayoutsAuth.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LayoutsAuthComponent { }
