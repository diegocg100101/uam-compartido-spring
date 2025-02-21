import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
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

  constructor(private api: ApiService) { }


  formulario = new FormGroup({
    "email": new FormControl(''),
    "password": new FormControl('')
  })

  login: UserLogin = new UserLogin();

  enviar() {
    console.log(this.formulario.value);
    this.api.postRequest('/auth/login', this.formulario.value).subscribe((data: any) => {
      localStorage.setItem('token', data.token);
    });
  }
}
