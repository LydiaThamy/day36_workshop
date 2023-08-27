import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Weather } from '../model/Weather';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  constructor(private http: HttpClient) { }

  // private url: string = "https://day36workshopapi-production.up.railway.app/api/"

  getCountryAPI(): Observable<string[]> {
    return this.http.get<string[]>("/cities")
  }

  addcountryAPI(city: string): Observable<any> {
    return this.http.post<any>("/cities/add", { city: city }, { observe: 'response' }) // httpOptions

    // let httpOptions = {
    //     headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    //     observe: 'response',};
  }

  getWeatherAPI(city: string): Observable<Weather> {
    let query: HttpParams = new HttpParams()
      .set("city", city)

    return this.http.get<Weather>("/weather", { params: query })
  }
}
