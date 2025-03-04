import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GrupoService {

  host = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) { }

  getGrupoInformation(): Observable<any> {
    return this.httpClient.get<any>(`${this.host}/grupos/add`, { withCredentials: true });
  }

  getLisGrupos(): Observable<any> {
    return this.httpClient.get<any>(`${this.host}/grupos/list`, { withCredentials: true });
  }

  altaGrupo(grupo: any) {
    return this.httpClient.post(`${this.host}/grupos/add`, grupo, { withCredentials: true, responseType: 'text' });
  }

  deleteGrupo(clave: String) {
    return this.httpClient.get(`${this.host}/grupos/delete/${clave}`, { withCredentials: true, responseType: 'text' });
  }

  editGrupo(grupo: any) {
    return this.httpClient.post(`${this.host}/grupos/edit`, grupo, { withCredentials: true, responseType: 'text' });
  }

  shareGrupo(clave : string, idunidades : any) {
    console.log(idunidades)
    return this.httpClient.post(`${this.host}/grupos/share/${clave}`, idunidades, { withCredentials: true, responseType: 'text' })
  }

}
