import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { UserServiceService } from '../../services/user-service.service';

@Component({
  selector: 'app-profesores',
  imports: [CommonModule],
  templateUrl: './profesores.component.html',
  styleUrl: './profesores.component.css'
})
export class ProfesoresComponent {

  profesores : any = {}
  listaOriginal : any = {}

  constructor(private userApi : UserServiceService) {}

  ngOnInit() {
    this.userApi.getAllUsers().subscribe((data : any) => {
      this.listaOriginal = data
      this.profesores = [...this.listaOriginal]
    });
  }

  buscar(event : Event) {
    this.profesores = [...this.listaOriginal];
    const elemento = event.target as HTMLInputElement
    const textoBusqueda = elemento.value.toLowerCase()

    if(textoBusqueda != '') {
      this.profesores = this.profesores.filter((profe : any) => {
        return this.quitarAcentos(profe.nombre + ' ' + profe.apellidopaterno + ' ' + profe.apellidomaterno).toLowerCase().includes(textoBusqueda) 
        || (profe.nombre + ' ' + profe.apellidopaterno + ' ' + profe.apellidomaterno).toLowerCase().includes(textoBusqueda)
        || profe.noeconomico.toLowerCase().includes(textoBusqueda)
        || profe.unidad.nombre.toLowerCase().includes(textoBusqueda);
      }) 
    }  
  }

  quitarAcentos(texto : string) : string {
    return texto.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
  }
}
