import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-grupos',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './grupos.component.html',
  styleUrl: './grupos.component.css'
})
export class GruposComponent {

  ngOnInit() {
  }

  eliminar(clave : any) {
    
  }

  enviar() {

  }

  editar() {

  }

  buscar(event : Event) {

  }

  ponerInfo(grupo : any) {

  }
}
