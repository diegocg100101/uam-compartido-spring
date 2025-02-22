import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  host = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) { }

  login(user: any) {
    return this.httpClient.post(`${this.host}/auth/login`, user, { withCredentials: true });
  }

  signup(user: any) {
    return this.httpClient.post(`${this.host}/auth/signup`, user, { withCredentials: true });
  }

  getInformation() {
    return this.httpClient.get(`${this.host}/auth/me`, { withCredentials: true });
  }
}
