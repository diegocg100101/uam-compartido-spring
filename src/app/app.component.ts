import { Component } from '@angular/core';
import { ApiService } from './services/api.service';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { UserAuth } from './user-auth'; 

@Component({
  selector: 'app-root',
  imports: [HeaderComponent, FooterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'uam-compartido';
  data: any[] = [];

  user : UserAuth = new UserAuth();

  constructor(private apiService: ApiService) {}

  // ngOnInit() {
  //   this.apiService.post('/auth/login', {email: 'usuario@example.com', password: '186251'}).subscribe((response: any) => {
  //     console.log(response);
  //   })
  // }
  
}
