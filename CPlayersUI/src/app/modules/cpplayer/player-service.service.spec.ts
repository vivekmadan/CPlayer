import {TestBed} from '@angular/core/testing';

import {PlayerServiceService} from './player-service.service';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

const testData = {
  'username' : 'testuser',
  'email' : 'test@test.com',
  'password' : 'test'
};

const playerData = {
  'pid' : '10001',
  'name' : 'testname',
  'country' : 'testcountry',
  'battingStyle' : 'testbattingstyle',
  'bowlingStyle' : 'testbowlingstyle',
  'profile' : 'testprofile',
  'imageUrl' : 'testimage',
  'count' : 1
};

describe('PlayerServiceService', () => {
  let playerService: PlayerServiceService;
  let httpTestingController: HttpTestingController;

  const testForRegister = 'http://localhost:8003/userservice/api/v1/register';
  const testForLogin = 'http://localhost:8003/userservice/api/v1/login';
  const springEndpoint = 'http://localhost:8003/favouriteservice/api/v1/favouriteservice/user/';
  const recommendationUrl = 'http://localhost:8003/playerrecommendationservice/api/v1/players';

  beforeEach(() => {
    TestBed.configureTestingModule({
    imports: [HttpClientTestingModule],
    providers: [PlayerServiceService]
  });

    playerService = TestBed.get(PlayerServiceService);
    httpTestingController = TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
    expect(playerService).toBeTruthy();
  });

  it('#register() should fetch proper response from http call', () => {
    playerService.register(testData).subscribe(res => {
      console.log('Response: ' + res);
      expect(res.body).toBe(testData);
    });

    const httpMockReq = httpTestingController.expectOne(testForRegister);
    expect(httpMockReq.request.method).toBe('POST');
    expect(httpMockReq.request.url).toEqual(testForRegister);
  });

  it('#login() should fetch proper response from http call', () => {
    playerService.loginUser(testData).subscribe(res => {
      console.log('Response: ' + res);
      expect(res.body).toBe(testData);
    });

    const httpMockReq = httpTestingController.expectOne(testForLogin);
    expect(httpMockReq.request.method).toBe('POST');
    expect(httpMockReq.request.url).toEqual(testForLogin);
  });

  it('#addToFavourites() should fetch proper response from http call', () => {
    playerService.addToFavourite(playerData).subscribe(res => {
      console.log('Response: ' + res);
      expect(res.body).toBe(playerData);
    });

    const url = springEndpoint + 'testuser/player';
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('POST');
    expect(httpMockReq.request.url).toEqual(url);
  });

  it('#getFavouritePlayers() should fetch proper response from http call', () => {
    playerService.getFavouritePlayers().subscribe(res => {});

    const url = springEndpoint + 'testuser/players';
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('GET');
    expect(httpMockReq.request.url).toEqual(url);
  });

  it('#deleteFromFavourites() should fetch proper response from http call', () => {
    playerService.deleteFromFavourites(playerData).subscribe(res => {
      console.log('Response: ' + res);
      expect(res.body).toBe(playerData);
    });

    const url = springEndpoint + 'testuser/player';
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('DELETE');
    expect(httpMockReq.request.url).toEqual(url);
  });

  it('#getRecommendedPlayers() should fetch proper response from http call', () => {
    playerService.getRecommendedPlayers().subscribe(res => {});
    const httpMockReq = httpTestingController.expectOne(recommendationUrl);
    expect(httpMockReq.request.method).toBe('GET');
    expect(httpMockReq.request.url).toEqual(recommendationUrl);
  });
});
