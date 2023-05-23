import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {RegistrationComponent} from "./pages/registration/registration.component";
import {AdminhomeComponent} from "./pages/adminhome/adminhome.component";
import {UserhomeComponent} from "./pages/userhome/userhome.component";
import {P1Component} from "./requests/p1/p1.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'ADMIN/home', component: AdminhomeComponent },
  { path: 'USER/home', component: UserhomeComponent },
  { path: 'create-p1-request', component: P1Component }
]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
