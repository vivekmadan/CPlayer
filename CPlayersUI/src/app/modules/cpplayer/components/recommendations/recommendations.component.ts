import { Component, OnInit } from '@angular/core';
import { Player } from '../../Player';
import { PlayerServiceService } from '../../player-service.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-recommendations',
  templateUrl: './recommendations.component.html',
  styleUrls: ['./recommendations.component.css']
})
export class RecommendationsComponent implements OnInit {
  players: Array<Player>;
  recommendation = true;

  constructor(
    private playerService: PlayerServiceService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit() {
    const message = 'There are no recommendations!';
    this.playerService.getRecommendedPlayers().subscribe(data => {
      this.players = data;

      if (data.length === 0) {
        this.snackBar.open(message, '', {duration: 1000});
      }
    });
  }

}
