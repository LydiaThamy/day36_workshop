import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CityComponent } from './component/city/city.component';
import { WeatherComponent } from './component/weather/weather.component';

const routes: Routes = [
  {path: '', component: CityComponent},
  {path: 'city', component: CityComponent},
  {path: 'weather', component: WeatherComponent},
  {path: '**', redirectTo: '/', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
