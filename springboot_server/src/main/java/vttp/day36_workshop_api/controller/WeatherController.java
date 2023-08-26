package vttp.day36_workshop_api.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.JsonObject;
import vttp.day36_workshop_api.service.WeatherService;

@RestController
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherController {
    

    @Autowired
    private WeatherService service;

    @GetMapping("/cities")
    public ResponseEntity<String> getCountries() {
        return ResponseEntity.ok(service.getCities().toString());
    }

    @GetMapping("/weather")
    public ResponseEntity<String> getWeather(@RequestParam("city") String city) throws IOException {

        Optional<JsonObject> opt = service.getWeather(city);

        if (opt.isEmpty())
            return ResponseEntity.status(404)
                .body("no such city");

        return ResponseEntity.status(200)
            .body(opt.get().toString());
    }
}
