import { inject } from '@angular/core';
import { CanMatchFn, Route, Router, UrlSegment } from '@angular/router';

import { firstValueFrom } from 'rxjs';
import { AuthService } from '../services/authService.service';


export const isAdminGuard: CanMatchFn = async (
  route: Route,
  segments: UrlSegment[]
) => {
  const authService = inject(AuthService);
  const router = inject(Router);

   await firstValueFrom(authService.checkAuthStatus());

 return authService.isAdmin();

};
