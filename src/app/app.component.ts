import { Component } from '@angular/core';
import { ApiService } from './services/api.service';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { UserModel } from './models/user-model';
import { UserLogin } from './models/user-login';

@Component({
  selector: 'app-root',
  imports: [HeaderComponent, FooterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  response: any[] = [];
  user : UserLogin = new UserLogin();
  
  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.user.email = 'usuario@example.com';
    this.user.password = '186251';
  }
  
}
