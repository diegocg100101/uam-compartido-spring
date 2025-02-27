import { Component, OnInit } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { UserServiceService } from '../../services/user-service.service';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup-form',
  imports: [RouterLink, CommonModule, ReactiveFormsModule],
  standalone: true,
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.css']
})
export class SignupFormComponent implements OnInit {

  signupInfo: any = {}; 
  modal : boolean = false;

  formulario = new FormGroup({
    "noeconomico": new FormControl('', Validators.compose([
      Validators.required
    ])),
    "nombre": new FormControl('', Validators.compose([
      Validators.required,
      Validators.pattern("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")
    ])),
    "apellidopaterno": new FormControl('', Validators.compose([
      Validators.required,
      Validators.pattern("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$")
    ])),
    "apellidomaterno": new FormControl('', Validators.compose([
      Validators.required,
      Validators.pattern("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$")
    ])),
    "unidad": new FormControl(null, Validators.compose([
      Validators.required
    ])),
    "division": new FormControl(null, Validators.compose([
      Validators.required
    ])), 
    "departamento": new FormControl(null, Validators.compose([
      Validators.required
    ])),
    "email" : new FormControl('', Validators.compose([
          Validators.required,
          Validators.email
        ])),
    "password": new FormControl('', Validators.compose([
          Validators.required,
          Validators.minLength(8)
        ]))
  });

  constructor(private userApi: UserServiceService, private router : Router) {}

  ngOnInit() {
    this.userApi.getSignupInformation().subscribe((data: any) => {
      this.signupInfo = data;
    });
  }

  enviar() {
    this.userApi.signup(this.formulario.value).subscribe({
      next: (response) => {
        console.log('Signup successful', response);
        this.router.navigate(['/'])
      },
      error: (error) => {
        console.error('Signup failed', error);
        this.modal = true
      }
    });
  }

  get email() {
    return this.formulario.get('email');
  }

  get password() {
    return this.formulario.get('password');
  }

  get nombre() {
    return this.formulario.get('nombre');
  }

  get noeconomico() {
    return this.formulario.get('noeconomico');
  }

  get apellidopaterno() {
    return this.formulario.get('apellidopaterno');
  }

  get apellidomaterno() {
    return this.formulario.get('apellidomaterno');
  }

  get unidad() {
    return this.formulario.get('unidad');
  }

  get division() {
    return this.formulario.get('division');
  }

  get departamento() {
    return this.formulario.get('departamento');
  }
}
