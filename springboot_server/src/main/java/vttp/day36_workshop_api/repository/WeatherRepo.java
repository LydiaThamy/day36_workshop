package vttp.day36_workshop_api.repository;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import vttp.day36_workshop_api.model.Weather;

@Repository
public class WeatherRepo {

    @Value("${open.weather.api.key}")
    private String apiKey;

    public Optional<Weather> getWeather(String city) throws IOException {

        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder
                .fromUriString("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("q", city.replaceAll(" ", "+"))
                .queryParam("appid", apiKey)
                .toUriString();

        String resp = restTemplate.getForEntity(url, String.class)
                .getBody();

        Weather weather = Weather.create(resp);

        return Optional.ofNullable(weather);
    }

}
