import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Config} from 'protractor';
import {catchError} from 'rxjs/operators';
import {throwError} from 'rxjs';
import {Injectable} from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class OrderingServiceService {


  constructor(private httpClient: HttpClient) {
  }

  getJsonData() {
    return this.httpClient.get<Config[]>('https://gist.githubusercontent.com/Aakash06/' +
      'fad46b64a573d4c152e899192b90b86c/raw/8bdfa0dfcce2411cb521b58916cd5556700c1ab1/menu.json').pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {

      console.error('An error occurred:', error.error.message);
    } else {

      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }

    return throwError(
      'Something bad happened; please try again later.');
  }
}
