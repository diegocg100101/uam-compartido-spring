import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptor implements HttpInterceptor {

  constructor(private router: Router, private auth: AuthService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("Interceptando petición:", req.url);

    const token = localStorage.getItem('token');

    if(token) {
      if(this.auth.isAuthenticated()){
        const request = req.clone({
          headers : req.headers.set('Authorization', `Bearer ${token}`)
        });
  
        return next.handle(request);
      } else {
        localStorage.removeItem('token');
        this.router.navigate(['']);
      }
    }

    return next.handle(req);
  }
}
