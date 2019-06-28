import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Player } from './Player';

@Injectable({
  providedIn: 'root'
})
export class PlayerServiceService {
  playerFinderApi: string;
  playerStatApi: string;
  apiKey: string;
  name: string;
  pid: string;
  players: Array<Player>;

  constructor(private http: HttpClient) {
    this.playerFinderApi = "https://cricapi.com/api/playerFinder";
    this.apiKey = "?apikey=qvg2iQ4VhEgrTKbgCxdxNn7GlUD2";
    this.name = "&name=";
    this.playerStatApi = "https://cricapi.com/api/playerStats";
    this.pid = "&pid=";
    this.players = [];
   }

   findPlayer(playerName: string): Observable<any> {
     const url = `${this.playerFinderApi}${this.apiKey}${this.name}${playerName}`;
     console.log("Player Finder URL -> " + url);
     return this.http.get(url);
   }

   playerStatistic(pid: string): Observable<any>{
    const url = `${this.playerStatApi}${this.apiKey}${this.pid}${pid}`;
    console.log("Stat URL: " + url);
    return this.http.get(url);
  }

   
  //  findPlayer(playerName: string): Array<Player> {
  //    const url = `${this.playerFinderApi}${this.apiKey}${this.name}${playerName}`;
  //    console.log("Player Finder URL -> " + url);

  //    const playerList = this.http.get(url);
  //    console.log("Player List -> " + playerList);
  //    playerList.subscribe(players => {
  //      console.log("Players -> " + players);
  //      const data = players['data'];
  //      data.forEach(targetData => {
  //        const pid = targetData["pid"];
  //        console.log("PID -> " + pid);

  //        this.playerStatistic(pid).subscribe(playerStats => {
  //         console.log("Full Name: " + playerStats['fullName']);
  //         var player = new Player();
  //         player.pid = pid;
  //         player.name = playerStats['name'];
  //         player.country = playerStats['country'];
  //         player.profile = playerStats['profile'];
  //         player.battingStyle = playerStats['battingStyle'];
  //         player.bowlingStyle = playerStats['bowlingStyle'];
  //         player.imageUrl = playerStats['imageURL'];
  //         this.players.push(player);
  //        });
  //      });
  //    });
  //    return this.players;
  //  }

   
}