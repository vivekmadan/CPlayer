import { Injectable } from '@angular/core';
import { PlayerServiceService } from './player-service.service';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor{

  constructor(private playerService: PlayerServiceService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("In intercept method: " + this.playerService.getToken());
    console.log("Index of: " + request.url.indexOf("cricapi"));
    if(request.url.indexOf("cricapi") == -1)
    {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.playerService.getToken()}`
        }
      });
    }
    return next.handle(request);
  }
}
