import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './modules/cpplayer/components/register/register.component';
import { CardContainerComponent } from './modules/cpplayer/components/card-container/card-container.component';
import { LoginComponent } from './modules/cpplayer/components/login/login.component';
import { FavouritesComponent } from './modules/cpplayer/components/favourites/favourites.component';
import { AuthguardService } from './modules/cpplayer/authguard.service';
import { RecommendationsComponent } from './modules/cpplayer/components/recommendations/recommendations.component';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'search',
    component: CardContainerComponent,
    canActivate: [AuthguardService]
  },
  {
    path: 'favourites',
    component: FavouritesComponent,
    canActivate: [AuthguardService]
  },
  {
    path: 'recommendations',
    component: RecommendationsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
