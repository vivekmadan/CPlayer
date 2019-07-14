import { Component, OnInit } from '@angular/core';
import { PlayerServiceService } from '../../player-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header-component',
  templateUrl: './header-component.component.html',
  styleUrls: ['./header-component.component.css']
})
export class HeaderComponentComponent implements OnInit {

  constructor(
    private playerService: PlayerServiceService,
    private route: Router
    ) { }

  ngOnInit() {
  }

  logout(){
    this.playerService.logout();
    console.log('Logout called.');
    this.route.navigate(['/login']);
  }

}
