import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  host = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) { }

  login(user: any) {
    return this.httpClient.post(`${this.host}/auth/login`, user, { withCredentials: true });
  }

  signup(user: any) : Observable<any> {
    return this.httpClient.post<any>(`${this.host}/auth/signup`, user, { withCredentials: true });
  }

  getSignupInformation() : Observable<any>{
    return this.httpClient.get<any>(`${this.host}/auth/signup`, { withCredentials: true });
  }

  getUserInformation() : Observable <any> {
    return this.httpClient.get(`${this.host}/user/me`, { withCredentials: true });
  }

  getAllUsers() : Observable <any> {
    return this.httpClient.get<any>(`${this.host}/user/all`, { withCredentials: true });
  }

  changePass(dto : any) {
    return this.httpClient.post(`${this.host}/auth/changepassword`, dto, { withCredentials: true, responseType: 'text'});
  }
}
