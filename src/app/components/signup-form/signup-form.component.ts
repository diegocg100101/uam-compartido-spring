import { Component, OnInit } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { UserServiceService } from '../../services/user-service.service';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

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
    "noeconomico": new FormControl(''),
    "nombre": new FormControl(''),
    "apellidopaterno": new FormControl(''),
    "apellidomaterno": new FormControl(''),
    "unidad": new FormControl(null),
    "division": new FormControl(null), 
    "departamento": new FormControl(null), 
    "email": new FormControl(''),
    "password": new FormControl('')
  });

  constructor(private userApi: UserServiceService, private router : Router) {}

  ngOnInit() {
    this.userApi.getSignupInformation().subscribe((data: any) => {
      this.signupInfo = data;
    });
  }

  enviar() {
    console.log("Formulario enviado:", this.formulario.value);
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
}
