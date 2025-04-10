import { User } from './../interfaces/Usuario.interface';
import { AuthResponse } from './../interfaces/LoginInterface';
import { HttpClient } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';
import { catchError, map, Observable, of, tap } from 'rxjs';
import { environment } from '../../../environments/environment.development';
import { rxResource } from '@angular/core/rxjs-interop';

type AuthStatus = 'checking' | 'authenticated' | 'not-authenticated';
const baseUrl = environment.base_url;

@Injectable({ providedIn: 'root' })
export class AuthService {
  private http = inject(HttpClient);
  private _token = signal<string | null>(localStorage.getItem('token'));
  private _user = signal<AuthResponse | null>(null);
  private _authStatus = signal<AuthStatus>('checking');

  user = computed(() => this._user());
  token = computed(() => this._token());
  isAdmin = computed(() => this._user()?.roles.includes('ADMIN') ?? false);

  checkStatusResource = rxResource({
    loader: () => this.checkAuthStatus(),
  });

  authStatus = computed<AuthStatus>(() => {
    if (this._authStatus() === 'checking') return 'checking';
    return this._user() ? 'authenticated' : 'not-authenticated';
  });

  constructor() {

    const token = this._token();
    if (token) {
      this.checkAuthStatus().subscribe();
    } else {
      this._authStatus.set('not-authenticated');
    }
  }

  login(email: string, password: string): Observable<boolean> {
    return this.http
      .post<AuthResponse>(`http://localhost:8080/login`, { email, password })
      .pipe(
        tap((resp)=>{
          localStorage.setItem("token",resp.token)
        } ),
        map((resp) => this.handleAuthSuccess(resp)),
        catchError((error: any) => this.handleAuthError(error))
      );
  }

  register(nombre: string, apellidos: string, email: string, password: string, terminosyCondiciones: boolean,aceptaMarketing:boolean): Observable<any> {
    return this.http.post(`${baseUrl}/usuarios/register`, {
      nombre,
      apellidos,
      email,
      password,
      terminosyCondiciones,
      aceptaMarketing
    });
  }
  checkAuthStatus(): Observable<boolean> {
    const token = localStorage.getItem("token");

    if (!token) {
      this._authStatus.set('not-authenticated');
      return of(false);
    }

    return this.http.get<AuthResponse>(`${baseUrl}/usuarios/check-status`, {
      headers: { 'Authorization': `Bearer ${token}` }
    }).pipe(
      map((resp) => this.handleAuthSuccess(resp)),
      catchError((error: any) => {
        this.handleAuthError(error);
        return of(false);
      })
    );
  }

  logout() {
    this._user.set(null);
    this._token.set(null);
    this._authStatus.set('not-authenticated');
    localStorage.removeItem('token');
  }

  private handleAuthSuccess(auth: AuthResponse): boolean {
    this._user.set(auth);
    this._authStatus.set('authenticated');
    this._token.set(auth.token);
    console.log('Token guardado:',localStorage.getItem("token"));
    return true;
  }


  private handleAuthError(error: any): Observable<boolean> {
    this.logout();
    return of(false);
  }
}
