import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { PlayerServiceService } from '../../player-service.service';
import { Player } from '../../Player';

@Component({
  selector: 'app-favourites',
  templateUrl: './favourites.component.html',
  styleUrls: ['./favourites.component.css']
})
export class FavouritesComponent implements OnInit {

  favourites = true;
  players: Array<Player>;

  constructor(
    private playerService: PlayerServiceService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit() {
    const message = 'Favourites is empty!';
    this.playerService.getFavouritePlayers().subscribe(data => {
      this.players = data;

      if(data.length === 0) {
        this.snackBar.open(message, '', {duration: 1000});
      }
    });
  }

  deletePlayer(player) {
    this.playerService.deleteFromFavourites(player).subscribe(data => {
      console.log('Deleted Player: ' + data);
      const index = this.players.indexOf(player);
      this.players.splice(index, 1);
      this.snackBar.open('Player successfully deleted!', '', {duration: 1000});
    });
  }

}
