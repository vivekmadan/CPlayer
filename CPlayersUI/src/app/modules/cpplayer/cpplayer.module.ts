import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardComponentComponent } from './components/card-component/card-component.component';
import { HeaderComponentComponent } from './components/header-component/header-component.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { AppRoutingModule } from '../../app-routing.module';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { MatCardModule } from '@angular/material/card';
import { CardContainerComponent } from './components/card-container/card-container.component';
import { DialogComponent } from './components/dialog/dialog.component';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatDialogModule} from '@angular/material/dialog';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { PlayerServiceService } from './player-service.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { InterceptorService } from './interceptor.service';
import { FavouritesComponent } from './components/favourites/favourites.component';
import { RecommendationsComponent } from './components/recommendations/recommendations.component';
import {MatBadgeModule} from '@angular/material/badge';

@NgModule({
  declarations: [
    CardComponentComponent,
    HeaderComponentComponent,
    RegisterComponent,
    LoginComponent,
    CardContainerComponent,
    DialogComponent,
    LoginComponent,
    FavouritesComponent,
    RecommendationsComponent
  ],
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
    MatCardModule,
    MatTooltipModule,
    MatDialogModule,
    MatSnackBarModule,
    MatBadgeModule
  ],
  entryComponents: [DialogComponent],
  exports: [
    CardComponentComponent,
    CardContainerComponent,
    HeaderComponentComponent,
    MatDialogModule,
    AppRoutingModule
   ],
  providers: [
    PlayerServiceService, {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true
    }
  ]
})
export class CpplayerModule { }
