import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { WeatherService } from 'src/app/service/weather.service';

@Component({
  selector: 'app-city',
  templateUrl: './city.component.html',
  styleUrls: ['./city.component.css']
})
export class CityComponent implements OnInit {

  // private service = inject(WeatherService)
  // private fb = inject(FormBuilder)

  constructor(private service: WeatherService, private fb: FormBuilder) {}

  cityForm: FormGroup
  cities$!: Observable<string[]>

  ngOnInit(): void {
    this.createForm()
    this.getCities()
  }

  getCities() {
    this.cities$ = this.service.getCountryAPI()
  }

  createForm(): void {
    this.cityForm = this.fb.group({
      city: this.fb.control<string>('')
    })
  }

  addCity() {
    const city = this.cityForm.get('city')

  }

}
