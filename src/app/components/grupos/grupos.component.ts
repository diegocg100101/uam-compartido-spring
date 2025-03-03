import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormArray, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { GrupoService } from '../../services/grupo.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-grupos',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './grupos.component.html',
  styleUrl: './grupos.component.css'
})

export class GruposComponent {

  listaGrupos: any = {};
  listaOriginal: any = {};
  infoGrupo: any = {};
  list: any[] = [];
  horario: string[] = [];
  jsonHorario: any;

  formulario = new FormGroup({
    "clavegrupo": new FormControl(''),
    "uea": new FormControl(null),
    "unidad": new FormControl(null),
    "horariolist": new FormControl<string[]>([]),
    "profesor": new FormControl(null),
    "cupounidad": new FormControl(''),
    "salon": new FormControl(null)
  });

  formularioEditar = new FormGroup({
    "clavegrupo": new FormControl(''),
    "uea": new FormControl(null),
    "unidad": new FormControl(null),
    "horariolist": new FormControl<string[]>([]),
    "profesor": new FormControl(null),
    "cupounidad": new FormControl(''),
    "salon": new FormControl(null)
  });

  constructor(private grupoApi: GrupoService, private router: Router) { }

  ngOnInit() {
    this.grupoApi.getGrupoInformation().subscribe((data) => {
      this.infoGrupo = data;
      console.log(this.infoGrupo)

      this.infoGrupo.usuarios = this.infoGrupo.usuarios.map((usuario: any) => ({
        "noeconomico": usuario.noeconomico,
        "email": usuario.email,
        "password": usuario.password,
        "rol": {
          "idrol": usuario.rol.idrol,
          "nombre": usuario.rol.nombre
        },
        "nombre": usuario.nombre,
        "apellidopaterno": usuario.apellidopaterno,
        "apellidomaterno": usuario.apellidomaterno,
        "unidad": {
          "idunidad": usuario.unidad.idunidad,
          "nombre": usuario.unidad.nombre
        },
        "departamento": {
          iddepartamento: usuario.departamento.iddepartamento,
          nombre: usuario.departamento.nombre
        },
        "division": {
          "iddivision": usuario.division.iddivision,
          "nombre": usuario.division.nombre
        }
      }))
    })



    this.grupoApi.getLisGrupos().subscribe((data) => {
      this.listaOriginal = data;
      this.listaGrupos.grupos = [...this.listaOriginal.grupos];

      console.log(this.listaGrupos)
    })
  }

  eliminar(clave: any) {
    this.grupoApi.deleteGrupo(clave).subscribe({
      next: (response) => {
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
    this.formulario.patchValue({ horariolist: this.horario })
    this.grupoApi.altaGrupo(this.formulario.value).subscribe({
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
    this.formularioEditar.patchValue({ horariolist: this.horario })
    const grupo = this.formularioEditar.value
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

  buscar(event: Event) {

  }

  ponerInfo(grupo: any) {
    console.log(grupo)
    this.formularioEditar.patchValue({
      clavegrupo: grupo.clavegrupo,
      uea: this.infoGrupo.ueas.find((claveuea: any) => claveuea.clave === grupo.uea.clave),
      unidad: this.infoGrupo.unidades.find((unidad: any) => unidad.idunidad === grupo.unidad.idunidad),
      profesor: this.infoGrupo.usuarios.find((noeconomico: any) => noeconomico.noeconomico === grupo.profesor.noeconomico),
      cupounidad: grupo.cupounidad,
      salon: this.infoGrupo.salones.find((salon: any) => salon.idsalon === grupo.salon.idsalon)
    })
  }

  actualizarHorario(event: Event) {
    const checkbox = event.target as HTMLInputElement;
    const valor = checkbox.value;

    if (checkbox.checked) {
      this.horario.push(valor);
    } else {
      this.horario = this.horario.filter(item => item !== valor);
    }
  }

  ponerHorario(grupo : any) {
    this.jsonHorario = JSON.parse(grupo.horario)
  }

  compartir() {

  }
}
