import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardComponentComponent } from './components/card-component/card-component.component';
import { HeaderComponentComponent } from './components/header-component/header-component.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatMenuModule} from '@angular/material/menu';
import { AppRoutingModule } from '../../app-routing.module';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {FormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import {MatCardModule} from '@angular/material/card';

@NgModule({
  declarations: [CardComponentComponent, HeaderComponentComponent, RegisterComponent, LoginComponent],
  imports: [
    CommonModule,
    MatToolbarModule,
    MatButtonModule,
    MatMenuModule,
    AppRoutingModule,
    MatFormFieldModule,
    MatIconModule,
    FormsModule,
    MatInputModule,
    MatCardModule
  ],
  exports: [
    CardComponentComponent,
    HeaderComponentComponent
  ]
})
export class CpplayerModule { }
