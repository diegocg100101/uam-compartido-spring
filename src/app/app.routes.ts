import { Routes } from '@angular/router';
import { MainComponent } from './components/main/main.component';
import { SignupFormComponent } from './components/signup-form/signup-form.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { AcercadeComponent } from './components/acercade/acercade.component';
import { MenuComponent } from './components/menu/menu.component';
import path from 'node:path';
import { GruposComponent } from './components/grupos/grupos.component';
import { InicioComponent } from './components/inicio/inicio.component';
import { UeasComponent } from './components/ueas/ueas.component';
import { PerfilComponent } from './components/perfil/perfil.component';

export const routes: Routes = [
    {
        path: '', 
        component: MainComponent,
        children: [
            { path: '', component: LoginFormComponent },
            { path: 'signup', component: SignupFormComponent }
        ]
    },
    {
        path: 'acercade', component: AcercadeComponent
    },
    {
        path: 'menu', component: MenuComponent,
        children: [
            { path: '', component: InicioComponent },
            { path: 'grupos', component: GruposComponent },
            { path: 'ueas', component: UeasComponent },
            { path: 'me', component: PerfilComponent }
        ]
    },
    {
        path: '**', redirectTo: '', pathMatch: 'full'
    }
    
];
