import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UeaService {

  host = 'http://localhost:8080';

  constructor(private httpClient : HttpClient) { }

  getUeaInformation() : Observable<any> {
    return this.httpClient.get<any>(`${this.host}/uea/add`, { withCredentials: true });
  }

  getListUeas() : Observable<any> {
    return this.httpClient.get<any>(`${this.host}/uea/list`, { withCredentials: true });
  }

  altaUea(uea : any) {
    return this.httpClient.post(`${this.host}/uea/add`, uea, { withCredentials: true, responseType: 'text' });
  }

  deleteUea(clave : String) {
    return this.httpClient.get(`${this.host}/uea/delete/${clave}`, { withCredentials: true, responseType: 'text' });
  }

  editUea(uea : any) {
    return this.httpClient.post(`${this.host}/uea/edit`, uea, { withCredentials: true, responseType: 'text'});
  }
}
