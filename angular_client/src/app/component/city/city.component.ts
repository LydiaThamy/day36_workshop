import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs';
import { WeatherService } from 'src/app/service/weather.service';

@Component({
  selector: 'app-city',
  templateUrl: './city.component.html',
  styleUrls: ['./city.component.css']
})
export class CityComponent implements OnInit, OnDestroy {

  constructor(private service: WeatherService, private fb: FormBuilder) { }

  cityForm: FormGroup

  sub$!: Subscription
  cities: string[] = []

  message: string = ''

  ngOnInit(): void {
    this.createForm()
    this.getCities()
  }

  getCities(): void {
    this.sub$ = this.service.getCountryAPI()
      .subscribe(
        data => {
          data.forEach(result => {
            this.cities.push(result)
          })
          this.cities.sort((a, b) => a.localeCompare(b))
        })
  }

  createForm(): void {
    this.cityForm = this.fb.group({
      city: this.fb.control<string>('')
    })
  }

  addCity(): void {
    let city: string = this.cityForm.value['city']

    // check if city is already on the list
    if (this.cities.includes(city)) {
      this.message = city + " already added!"

      // add city to SQL
    } else {

      let statusCode: number

      this.service.addcountryAPI(city.toLowerCase())
        .subscribe({
          next: response => { statusCode = response.status },

          // check if city is added
          complete: () => {
            if (statusCode != 200) {
              this.message = city + " is not a city!"
      
            } else {
              this.message = city + " saved!"
              
              // refresh cities list
              this.cities = []
              this.getCities()
            }
          }
        })
    }
  }

  ngOnDestroy(): void {
    this.sub$.unsubscribe()
  }
}
