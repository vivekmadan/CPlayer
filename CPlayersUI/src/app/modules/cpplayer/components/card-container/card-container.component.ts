import { Component, OnInit } from '@angular/core';
import { PlayerServiceService } from '../../player-service.service';
import { Player } from '../../Player';

@Component({
  selector: 'app-card-container',
  templateUrl: './card-container.component.html',
  styleUrls: ['./card-container.component.css']
})
export class CardContainerComponent implements OnInit {

  players: Array<Player>;
  defaultImageUrl: string;

  constructor(private playerService: PlayerServiceService) { 
    this.players = [];
    this.defaultImageUrl = '../../../../../assets/images/default.png';
  }

  ngOnInit() {
  }

  onEnter(value: string) {
    this.players = [];
    console.log("Value: " + value);
    this.playerService.findPlayer(value).subscribe(players => {
      console.log("Players => " + players);
      const data = players['data'];
      data.forEach(player => {
        const pid = player['pid'];
        console.log("Pid: " + pid);

        this.playerService.playerStatistic(pid).subscribe(playerStats => {
          console.log("Full Name: " + playerStats['name']);
          console.log("URL " + playerStats['imageURL']);
          var player = new Player();
          player.pid = pid;
          player.name = playerStats['name'];
          player.country = playerStats['country'];
          player.profile = playerStats['profile'];
          player.battingStyle = playerStats['battingStyle'];
          player.bowlingStyle = playerStats['bowlingStyle'];
          if(playerStats['imageURL'] == null)
            player.imageUrl = this.defaultImageUrl;
          else
            player.imageUrl = playerStats['imageURL'];
          this.players.push(player);
        });
      });
    });
  }

}
