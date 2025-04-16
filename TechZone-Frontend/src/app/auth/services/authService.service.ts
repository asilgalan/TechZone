import { User } from './../interfaces/Usuario.interface';
import { AuthResponse } from './../interfaces/LoginInterface';
import { HttpClient } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';
import { catchError, map, Observable, of, tap } from 'rxjs';
import { environment } from '../../../environments/environment.development';
import { rxResource } from '@angular/core/rxjs-interop';
import { ProductoService } from '../../products/services/Producto.service';

type AuthStatus = 'checking' | 'authenticated' | 'not-authenticated';
const baseUrl = environment.base_url;

@Injectable({ providedIn: 'root' })
export class AuthService {
  private http = inject(HttpClient);
  private _token = signal<string | null>(localStorage.getItem('token'));
  private _loginvalido=signal(false);
  private _user = signal<User | null>(null);

  constructor() {
    const token = this._token();
    if (token) {
      this.checkAuthStatus().subscribe();
    } else {
      this._authStatus.set('not-authenticated');
    }
    const userData = localStorage.getItem('user');
    if (userData) {
      try {
        const parsedUser = JSON.parse(userData);
        this._user.set(parsedUser);
      } catch (e) {
        console.error('Error al parsear el user desde localStorage', e);
        this._user.set(null);
      }
    }
  }



  private _authStatus = signal<AuthStatus>('checking');

  user = computed(() => this._user());
  token = computed(() => this._token());
  isAdmin = computed(() => this._user()?.roles.includes('ADMIN') ?? false);
  loginvalido=computed(() =>  this._loginvalido())
  checkStatusResource = rxResource({
    loader: () => this.checkAuthStatus(),
  });

  authStatus = computed<AuthStatus>(() => {
    if (this._authStatus() === 'checking') return 'checking';
    return this._user() ? 'authenticated' : 'not-authenticated';
  });



  login(email: string, password: string): Observable<boolean> {
    return this.http
      .post<AuthResponse>(`http://localhost:8080/login`, { email, password })
      .pipe(
        tap((resp)=>{
          localStorage.setItem("token",resp.token)
          localStorage.setItem("user",JSON.stringify(resp.user))
          this._loginvalido.set(true);
          setTimeout(() => {this._loginvalido.set(false)},4000)

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
    localStorage.removeItem('user')

  }

  private handleAuthSuccess(auth:AuthResponse): boolean {
    this._user.set(auth.user);
    this._authStatus.set('authenticated');
    this._token.set(auth.token);
    localStorage.setItem("user", JSON.stringify(auth.user));
    console.log('Token guardado:',localStorage.getItem("token"));
    return true;
  }


  private handleAuthError(error: any): Observable<boolean> {
    this.logout();
    return of(false);
  }
}
