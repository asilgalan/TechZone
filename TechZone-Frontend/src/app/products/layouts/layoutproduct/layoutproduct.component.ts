import { ChangeDetectionStrategy, Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-layoutproduct',
  imports: [RouterOutlet],
  templateUrl: './layoutproduct.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LayoutproductComponent { }
