import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Weather } from '../model/Weather';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  constructor(private http: HttpClient) { }

  // private url: string = "https://day36workshopapi-production.up.railway.app/api/"

  getCountryAPI(): Observable<string[]> {
    return this.http.get<string[]>("/cities")
  }

  getWeatherAPI(city: string): Observable<Weather> {
    let query: HttpParams = new HttpParams()
      .set("city", city)

    return this.http.get<Weather>("/weather", { params: query })
  }
}
