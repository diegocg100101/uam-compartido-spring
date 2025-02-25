import { Component } from '@angular/core';
import { UserServiceService } from '../../services/user-service.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-inicio',
  imports: [CommonModule],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css'
})
export class InicioComponent {

  nombre : any;

  constructor(private userApi : UserServiceService){}

  ngOnInit(){
    this.userApi.getUserInformation().subscribe((data:any) => {
      this.nombre = data.nombre
    })
  }

}
