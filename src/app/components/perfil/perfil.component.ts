import { Component } from '@angular/core';
import { UserServiceService } from '../../services/user-service.service';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserLogin } from '../../models/user-login';
import { ChangePass } from '../../models/changepass-model';
import { passwordMatchValidator } from '../../validators/password-match.validator';

@Component({
  selector: 'app-perfil',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.css'
})
export class PerfilComponent {

  user: any = {}
  error : boolean = false;
  exito : boolean = false;

  passwordForm = new FormGroup({
    "oldpassword" : new FormControl('', Validators.compose([
          Validators.required,
          Validators.minLength(8)
        ])),
    "newpassword" : new FormControl('', Validators.compose([
              Validators.required,
              Validators.minLength(8)
            ])),
    "confirmpassword" : new FormControl('', Validators.compose([
          Validators.required,
          Validators.minLength(8),
        ]))
  }, { validators: passwordMatchValidator() })


  constructor(private apiUser: UserServiceService) { }

  ngOnInit() {
    this.apiUser.getUserInformation().subscribe((data: any) => {
      this.user = data;
      console.log(data)
    })
  }

  changePassword() {
    const form = this.passwordForm
    const dto: ChangePass = new ChangePass(
      this.user.email,
      form.get('oldpassword')!.value,
      form.get('newpassword')!.value,
      form.get('confirmpassword')!.value
    );

    this.apiUser.changePass(dto).subscribe({
      next: (data) => {
        console.log(data)
        if(data == 'true') this.exito = true;
        else this.error = true;
      }, 
      error: (data) => {
        this.error = true
      }
     })
    
  }

  get oldpassword () {
    return this.passwordForm.get('oldpassword');
  }

  get newpassword() {
    return this.passwordForm.get('newpassword');
  }

  get confirmpassword() {
    return this.passwordForm.get('confirmpassword');
  }

}
