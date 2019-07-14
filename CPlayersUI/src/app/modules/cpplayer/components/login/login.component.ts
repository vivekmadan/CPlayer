import { Component, OnInit } from '@angular/core';
import { User } from '../../User';
import { PlayerServiceService } from '../../player-service.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
export const TOKEN = "token";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User;
  constructor(
    private playerService: PlayerServiceService,
    private snackBar: MatSnackBar,
    private route: Router
  ) {
    this.user = new User();
   }

  ngOnInit() {
  }

  login() {
    this.playerService.loginUser(this.user).subscribe(data => {
      console.log("Data: " + data);
      if(data) {
        console.log("Incoming Token: " + data.body['token']);
        localStorage.setItem(TOKEN, data.body['token']);
        this.snackBar.open(data.body['message'], "", {duration: 1000});
        this.route.navigate(['/search']);
      }
    });
  }

}
