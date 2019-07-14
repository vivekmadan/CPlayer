import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { PlayerServiceService } from './player-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthguardService implements CanActivate {

  constructor(
    private playerService: PlayerServiceService,
    private router: Router
  ) { }

  canActivate() {
    if (this.playerService.isTokenExpired()) {
      console.log('In CanActivate method');
      return true;
    }

    this.router.navigate(['/login']);
    return false;
  }
}
