import { inject } from "@angular/core";
import { CanMatch, CanMatchFn, Route, Router, UrlSegment } from "@angular/router";
import { AuthService } from "../services/authService.service";
import { firstValueFrom } from "rxjs";



export const NotAuthenticatedGuard: CanMatchFn = async (
  route:Route,
  segments:UrlSegment[]
)=>{
  const authService=inject(AuthService)
  const router=inject(Router)

  const isAuthenticated=await firstValueFrom(authService.checkAuthStatus())

  if(isAuthenticated){
    router.navigateByUrl('/');
    return false;
  }

  return true;

}
