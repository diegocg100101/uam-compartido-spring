import { Component } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, Validator, ReactiveFormsModule } from '@angular/forms';
import { UserLogin } from '../../models/user-login';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-login-form',
  imports: [RouterLink, CommonModule, ReactiveFormsModule],
  standalone: true,
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.css'
})
export class LoginFormComponent {

  modal = false;
  mensaje = 'Usuario o constraseña incorrecta';

  constructor(private api: ApiService, private router: Router) { }


  formulario = new FormGroup({
    "email": new FormControl(''),
    "password": new FormControl('')
  })

  login: UserLogin = new UserLogin();

  enviar() {
    this.api.postRequest('/auth/login', this.formulario.value).subscribe({
      next : (data : any) => {
        localStorage.setItem('token', data.token);
        this.router.navigate(['/menu']);
      },
      error : (error : any) => {
        console.log(error);
        this.modal = true
      }
    });
  }
}
