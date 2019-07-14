import { Component, OnInit } from '@angular/core';
import { User } from '../../User';
import { PlayerServiceService } from '../../player-service.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: User;

  constructor(
    private cpPlayerService: PlayerServiceService,
    private matSnackBar: MatSnackBar,
    private router: Router
    ) { 
      this.user = new User();
    }

  ngOnInit() {
    
  }

  register() {
    this.cpPlayerService.register(this.user).subscribe(data => {
      if(data.status == 201) {
        this.matSnackBar.open("User succesfully registered", "", {duration: 1000});
        this.router.navigate(["/login"]);
      }
    },
    error => {
      console.log(error);
    });
  }
}
