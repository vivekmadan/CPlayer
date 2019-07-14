import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Player } from '../../Player';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  player: Player;

  constructor(public dialogRef: MatDialogRef<DialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Player) {
      this.player = new Player();
      this.player.pid = data.pid;
      this.player.name = data.name;
      this.player.country = data.country;
      this.player.battingStyle = data.battingStyle;        
      this.player.bowlingStyle = data.bowlingStyle;     

      this.player.profile = data.profile;
      this.player.imageUrl = data.imageUrl;
     }

  ngOnInit() {
  }

  onNoClick(){
    this.dialogRef.close();
  }

}
