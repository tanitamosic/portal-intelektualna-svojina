import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {RegistrationComponent} from "./pages/registration/registration.component";
import {AdminhomeComponent} from "./pages/adminhome/adminhome.component";
import {UserhomeComponent} from "./pages/userhome/userhome.component";
import {P1Component} from "./requests/p1/p1.component";
import {A1Component} from "./requests/a1/a1.component";
import {DecisionComponent} from "./decision/decision.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'ADMIN/home', component: AdminhomeComponent },
  { path: 'USER/home', component: UserhomeComponent },
  { path: 'create-p1-request', component: P1Component },
  { path: 'create-a1-request', component: A1Component },
  { path: 'make-decision', component: DecisionComponent }
]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
