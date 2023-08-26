import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Weather } from 'src/app/model/Weather';
import { WeatherService } from 'src/app/service/weather.service';

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent implements OnInit {

  constructor(
    private weatherSvc: WeatherService, 
    private aRoute: ActivatedRoute,
    private router: Router
    ) {}

  city!: string
  weather$: Observable<Weather>

  ngOnInit() {
    this.city = this.aRoute.snapshot.queryParams.city
    this.weather$ = this.weatherSvc.getWeatherAPI(this.city)
  }

  backHome() {
    this.router.navigate(["/"])
  }

}
