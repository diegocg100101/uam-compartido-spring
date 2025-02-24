import { Component } from '@angular/core';
import { UserServiceService } from '../../services/user-service.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-perfil',
  imports: [CommonModule],
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.css'
})
export class PerfilComponent {

  user: any = {}

  constructor(private apiUser : UserServiceService) {}

  ngOnInit() {
    this.apiUser.getUserInformation().subscribe((data : any) => {
      this.user = data;
      console.log(data)
    })
  }

}
