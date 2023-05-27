import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from "@angular/forms";
import { LoginComponent } from './pages/login/login.component';
import { RegistrationComponent } from './pages/registration/registration.component';
import { HttpClientModule } from '@angular/common/http';
import { UserhomeComponent } from './pages/userhome/userhome.component';
import { AdminhomeComponent } from './pages/adminhome/adminhome.component';
import { A1Component } from './requests/a1/a1.component';
import { P1Component } from './requests/p1/p1.component';
import { Z1Component } from './requests/z1/z1.component';
import { DecisionComponent } from './decision/decision.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    UserhomeComponent,
    AdminhomeComponent,
    A1Component,
    P1Component,
    Z1Component,
    DecisionComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
