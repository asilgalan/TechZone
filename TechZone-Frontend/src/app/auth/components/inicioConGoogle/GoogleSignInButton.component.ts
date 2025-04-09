// components/google-signin-button.component.ts
import { Component, AfterViewInit, inject } from '@angular/core';
import { Router } from '@angular/router';

declare const google: any;

@Component({
  selector: 'app-google-signin-button',
  standalone: true,
  template: `<div id="google-login-button" class="mt-4"></div>`,
})
export class GoogleSignInButtonComponent implements AfterViewInit {
  private router = inject(Router);

  ngAfterViewInit() {
    this.loadGoogleScript();
  }

  private loadGoogleScript() {
    if (typeof google !== 'undefined') return;

    const script = document.createElement('script');
    script.src = 'https://accounts.google.com/gsi/client';
    script.async = true;
    script.defer = true;
    document.head.appendChild(script);

    script.onload = () => {
      google!.accounts.id.initialize({
        client_id: '158739471110-2do0097b7kaag10ick0he03nkd1he0fs.apps.googleusercontent.com',
        callback: (response: any) => this.handleCredentialResponse(response),
      });

      google!.accounts.id.renderButton(
        document.getElementById('google-login-button'),
        {
          theme: 'outline',
          size: 'large',
          width: 300,
          text: 'signin_with'
        }
      );
    };
  }

  private handleCredentialResponse(response: any) {
    // Decodifica el token JWT para obtener los datos del usuario
    const payload = JSON.parse(atob(response.credential.split('.')[1]));

    console.log('Usuario de Google:', payload);
    // Aquí enviarías el token a tu backend para autenticación
    // Ejemplo: this.authService.loginWithGoogle(response.credential);

    // Redirigir después del login
    this.router.navigate(['/']);
  }
}
