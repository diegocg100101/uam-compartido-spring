import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { GrupoService } from '../../services/grupo.service';
import { Router } from '@angular/router';
import { response } from 'express';

@Component({
  selector: 'app-grupos',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './grupos.component.html',
  styleUrl: './grupos.component.css'
})

export class GruposComponent {

  listaGrupos : any = {};
  listaOriginal : any = {};
  infoGrupo : any = {};
  list : any[]=[];

  formulario1 = new FormGroup({
    "claveGrupo": new FormControl(''),
    "claveUea": new FormControl(null),
    "unidad": new FormControl(null),
    "horario": new FormControl(null),
    "noEconomico": new FormControl(null),
    "cupoUnidad": new FormControl(''),
    "salon": new FormControl(null)
  });

  formulario1Editar = new FormGroup({
    "claveGrupo": new FormControl(''),
    "claveUea": new FormControl(null),
    "unidad": new FormControl(null),
    "horario": new FormControl(null),
    "noEconomico": new FormControl(null),
    "cupoUnidad": new FormControl(''),
    "salon": new FormControl(null)
  });

  constructor(private grupoApi: GrupoService, private router: Router){}
  
  ngOnInit(){
    this.grupoApi.getGrupoInformation().subscribe((data) => {
    this.infoGrupo = data;
    console.log(this.infoGrupo)} )



    this.grupoApi.getLisGrupos().subscribe((data) => {
      this.listaOriginal = data;
      this.listaGrupos.grupos = [...this.listaOriginal.grupos];
    })
  }

  eliminar(clave : any) {
    this.grupoApi.deleteGrupo(clave).subscribe({
      next : (response) =>{
        console.log("Petición Exitosa")
        this.ngOnInit();
      },
      error: (error) => {
        /* TODO */
        console.log("Error al hacer la petición")
      }
    })

  }

  enviar() {
    console.log(this.formulario1.value)
    this.grupoApi.altaGrupo(this.formulario1.value).subscribe({
      next: (response) => {
        /* TODO */
        console.log("Petición exitosa");
        this.ngOnInit();
      },
      error: (error) => {
        /* TODO */
        console.log("Error al hacer la petición")
      }
    })

  }

  editar() {
    const grupo = this.formulario1Editar.value

    this.grupoApi.editGrupo(grupo).subscribe({
      next: (data) => {
        console.log("Petición exitosa");
        this.ngOnInit();
      },
      error: (err) => {
        console.log("Error al hacer la petición");
      },
    })

  }

  buscar(event : Event) {

  }

  ponerInfo(grupo : any) {
    this.formulario1Editar.patchValue({
      claveGrupo : grupo.clave,
      claveUea : this.infoGrupo.uea.find((claveuea: any) => claveuea.claveuea === grupo.claveuea.clave),
      unidad : this.infoGrupo.unidades.find((unidad : any ) => unidad.idunidad === grupo.unidad.idunidad),
      //horario: this.infoGrupo.grupos.find((horario : any) => horario.)
      noEconomico: this.infoGrupo.usuarios.find((noeconomico: any) => noeconomico.noeconomico === grupo.profesores.noeconomico),
      cupoUnidad: grupo.cupoUnidad,
      salon: this.infoGrupo.salon.find((salon : any ) => salon.idsalon === grupo.salon.idsalon)
    })

  }
}
