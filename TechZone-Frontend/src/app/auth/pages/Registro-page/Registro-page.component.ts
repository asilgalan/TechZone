import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { GoogleSignInButtonComponent } from "../../components/inicioConGoogle/GoogleSignInButton.component";
import { AuthService } from '../../services/authService.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormUtilidades } from '../../../utils/form-utilidades';
import { delay, tap } from 'rxjs';

@Component({
  selector: 'app-registro-page',
  imports: [RouterLink, GoogleSignInButtonComponent,ReactiveFormsModule],
  templateUrl: './Registro-page.component.html'
})
export class RegistroPageComponent {

  autService=inject(AuthService)
  router=inject(Router)
  hasError=signal(false);
  formUtils=FormUtilidades;
    submit=signal(false);
  fb=inject(FormBuilder);

  registerForm:FormGroup=this.fb.group({
    nombre:['',[Validators.required,Validators.minLength(2)]],
    apellidos:['',[Validators.required,Validators.minLength(2)]],
    email:['',[Validators.required,Validators.pattern(this.formUtils.emailPattern)]],
    terminosyCondiciones:[false,[Validators.requiredTrue]],
    password:['',[Validators.required,Validators.minLength(6)]],
    confirmPassword:['',[Validators.required]],
    aceptaMarketing:[true]
  },{
    validators:[
      this.formUtils.igualPassword("password","confirmPassword")
    ]
  })

  onSubmit() {

    this.submit.set(true);
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }

    const { email, password, nombre, apellidos, terminosyCondiciones, aceptaMarketing } = this.registerForm.value;

    this.autService.register(nombre, apellidos, email, password, terminosyCondiciones, aceptaMarketing)
      .pipe(
        tap(() => this.hasError.set(true)),
        delay(3000),
        tap(() => this.router.navigateByUrl("/auth/login"))
      )
      .subscribe();
  }

}
