import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  host = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) { }

  // Método para consumir el API con el método GET
  public getRequest(endpoint: string) {
    return this.httpClient.get(`${this.host}${endpoint}`, { withCredentials: true});
  }

  public postRequest(endpoint: string, body: any) {
    return this.httpClient.post(`${this.host}${endpoint}`, body, { withCredentials: true});
  }
}
