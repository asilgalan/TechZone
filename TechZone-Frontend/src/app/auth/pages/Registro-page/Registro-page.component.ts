import { ChangeDetectionStrategy, Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { GoogleSignInButtonComponent } from "../../components/inicioConGoogle/GoogleSignInButton.component";

@Component({
  selector: 'app-registro-page',
  imports: [RouterLink, GoogleSignInButtonComponent],
  templateUrl: './Registro-page.component.html'
})
export class RegistroPageComponent { }
