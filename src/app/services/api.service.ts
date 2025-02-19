import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { response } from 'express';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  host = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) { }

  // Método para consumir el API con el método GET
  public get(endpoint: string) {
    return this.httpClient.get(`${this.host}${endpoint}`, {responseType: 'text', withCredentials: true});
  }

}
