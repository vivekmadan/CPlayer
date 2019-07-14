import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Player } from './Player';
export const USER_NAME = 'username';
export const TOKEN = 'token';

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
  registerEndpoint: string;
  loginEndpoint: string;
  springEndPoint: string;
  username: string;
  recommendedPlayerUrl: string;

  constructor(private http: HttpClient) {
    this.playerFinderApi = 'https://cricapi.com/api/playerFinder';
    this.apiKey = '?apikey=qvg2iQ4VhEgrTKbgCxdxNn7GlUD2';
    this.name = '&name=';
    this.playerStatApi = 'https://cricapi.com/api/playerStats';
    this.pid = '&pid=';
    this.registerEndpoint = 'http://localhost:8003/userservice/api/v1/register';
    this.loginEndpoint = 'http://localhost:8003/userservice/api/v1/login';
    this.springEndPoint = 'http://localhost:8003/favouriteservice/api/v1/favouriteservice/user/';
    this.recommendedPlayerUrl = 'http://localhost:8003/playerrecommendationservice/api/v1/players';
    this.players = [];
   }

   findPlayer(playerName: string): Observable<any> {
     const url = `${this.playerFinderApi}${this.apiKey}${this.name}${playerName}`;
     console.log('Player Finder URL -> ' + url);
     return this.http.get(url);
   }

   playerStatistic(pid: string): Observable<any> {
    const url = `${this.playerStatApi}${this.apiKey}${this.pid}${pid}`;
    console.log('Stat URL: ' + url);
    return this.http.get(url);
  }

  register(user) {
    const url = this.registerEndpoint;
    return this.http.post(url, user, {observe: 'response'});
  }

  loginUser(user) {
    const url = this.loginEndpoint;
    sessionStorage.setItem(USER_NAME, user.username);
    return this.http.post(url, user, {observe: 'response'});
  }

  addToFavourite(player) {
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.springEndPoint + this.username + '/player';
    console.log('Add to Favourite URL: ' + url);
    return this.http.post(url, player, {observe: 'response'});
  }

  getToken(): string {
    return localStorage.getItem(TOKEN);
  }

  isTokenExpired(token?: string): boolean {
    if (localStorage.getItem(TOKEN)) {
      return true;
    } else {
      return false;
    }
  }

  logout() {
    sessionStorage.removeItem(USER_NAME);
    sessionStorage.clear();
    localStorage.removeItem(TOKEN);
    localStorage.clear();
    console.log('Logged Out');
  }

  getFavouritePlayers(): Observable<Player[]> {
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.springEndPoint + this.username + '/players';
    console.log('Favourites URL: ' + url);
    return this.http.get<Player[]>(url);
  }

  deleteFromFavourites(player: Player): Observable<any> {
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.springEndPoint + this.username + '/player';
    console.log('Delete from Favourites URL: ' + url);

    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      body: player
    };
    return this.http.delete(url, options);
  }

  getRecommendedPlayers(): Observable<Player[]> {
    return this.http.get<Player[]>(this.recommendedPlayerUrl);
  }
}
