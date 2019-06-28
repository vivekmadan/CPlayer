import { Component, OnInit, Input } from '@angular/core';
import { Player } from '../../Player';

@Component({
  selector: 'app-card-component',
  templateUrl: './card-component.component.html',
  styleUrls: ['./card-component.component.css']
})
export class CardComponentComponent implements OnInit {

  @Input()
  player: Player;
  
  constructor() { }

  ngOnInit() {
  }

}
