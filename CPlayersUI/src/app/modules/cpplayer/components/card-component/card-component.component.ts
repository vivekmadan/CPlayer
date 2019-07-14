import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Player } from '../../Player';
import { DialogComponent } from '../dialog/dialog.component';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { PlayerServiceService } from '../../player-service.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-card-component',
  templateUrl: './card-component.component.html',
  styleUrls: ['./card-component.component.css']
})
export class CardComponentComponent implements OnInit {

  @Input()
  player: Player;

  @Input()
  favourites: boolean;

  @Output()
  deletedPlayer = new EventEmitter();

  @Input()
  recommendation: boolean;

  constructor(
    private dialog: MatDialog,
    private playerService: PlayerServiceService,
    private snackBar: MatSnackBar
    ) { }

  ngOnInit() {
  }

  showDialog():void {
    console.log('showDialog method called.');
    const dialogRef = this.dialog.open(DialogComponent, {
      width: '1000px',
      data: {
        'name': this.player.name,
        'pid': this.player.pid,
        'country': this.player.country,
        'battingStyle': this.player.battingStyle,
        'bowlingStyle': this.player.bowlingStyle,
        'profile': this.player.profile,
        'imageUrl': this.player.imageUrl
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Result: ', result);
    });
  }

  addToFavourite() {
    this.playerService.addToFavourite(this.player).subscribe(data => {
      if (data.status === 201) {
        this.snackBar.open('Player succesfully added!', '', {duration: 1000});
      }
    },
    error => {
      console.log(error);
      this.snackBar.open(error.error.message, '', {duration: 1000});
    });
  }

  deleteFromFavourites(player) {
    this.deletedPlayer.emit(player);
  }
}
