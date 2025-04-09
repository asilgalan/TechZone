import { Component, inject, Input, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { GoogleSignInButtonComponent } from "../../components/inicioConGoogle/GoogleSignInButton.component";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms"
import { FormUtilidades } from '../../../utils/form-utilidades';
import { AuthService } from '../../services/authService.service';
@Component({
  selector: 'app-login-page',
  imports: [RouterLink, GoogleSignInButtonComponent,ReactiveFormsModule],
  templateUrl: './Login-page.component.html',

})
export class LoginPageComponent {

private fb=inject(FormBuilder)
authService=inject(AuthService)
router=inject(Router)
hasError=signal(false);
formUtil=FormUtilidades;

@Input()
hasLogin=signal(false);

loginForm:FormGroup=this.fb.group({
  email:['',[Validators.required,Validators.pattern(this.formUtil.emailPattern)]],
  password:['',[Validators.required,Validators.minLength(6)]]

})

onSubmit(){

  if(this.loginForm.invalid){
    this.loginForm.markAllAsTouched();
    return;
  }

  const {email='',password=''} =this.loginForm.value;

  this.authService.login(email,password).subscribe((isLogin)=>{
    if(isLogin){

      this.hasLogin.set(true)
      this.router.navigateByUrl('/')
      return;
    }

    this.hasError.set(true);

    setTimeout(() => {
      this.hasError.set(false);

    },2000)


  })



}

 }
