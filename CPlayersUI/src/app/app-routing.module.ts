import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './modules/cpplayer/components/register/register.component';
import { CardContainerComponent } from './modules/cpplayer/components/card-container/card-container.component';

const routes: Routes = [
  {
    path: "",
    component: RegisterComponent
  },
  {
    path: "Search",
    component: CardContainerComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
