import { AppPage } from './app.po';
import {browser, by, protractor} from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  /*it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to CPlayersUI!');
  });*/

  it('should display the title of application', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('CPlayersUI');
  });

  it('should directed to /register route', () => {
    browser.element(by.css('.register-user')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
    browser.driver.sleep(1000);
  })

  it('should be able to register user', () => {
    browser.element(by.id('username')).sendKeys('test123');
    browser.element(by.id('email')).sendKeys('test123');
    browser.element(by.id('password')).sendKeys('test123');
    browser.element(by.css('.register-user')).click();
    browser.driver.sleep(1000);
  });

  it('should be able to login user', () => {
    browser.element(by.id('username')).sendKeys('test123');
    browser.element(by.id('password')).sendKeys('test123');
    browser.element(by.css('.login-click')).click();
    browser.driver.sleep(1000);
  });

  it('should search player from search box', () =>{
    browser.element(by.id('search')).sendKeys('Singh');
    browser.element(by.id('search')).sendKeys(protractor.Key.ENTER);
    browser.driver.sleep(5000);
  });

  it('should be able to open details dialog box', () => {
    browser.sleep(500);
    const players = browser.element.all(by.css('.example-card'));
    browser.sleep(1000);
    browser.element(by.css('.details')).click();
    browser.sleep(1000);
    browser.element(by.css('.close')).click();
    browser.sleep(1000);
  });

  it('should be able to add player in favourites', () => {
    browser.driver.manage().window().maximize();
    const players = browser.element.all(by.css('.example-card'));
    browser.sleep(1000);
    browser.element(by.css('.addButton')).click();
    browser.sleep(5000);
  });

  it('should be able to get all data from Favourites', () => {
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-button-favourites')).click();
    expect(browser.getCurrentUrl()).toContain('/favourites');
    browser.driver.sleep(1000);
  });

  it('should be able to get all data from recommendations', () => {
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-button-recommendations')).click();
    expect(browser.getCurrentUrl()).toContain('/recommendations');
    browser.driver.sleep(1000);
  });

  it('should be able to logged out from the application', () => {
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-button-logout')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
    browser.driver.sleep(1000);
  });
});
