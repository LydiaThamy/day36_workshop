package vttp.day36_workshop_api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import vttp.day36_workshop_api.service.WeatherService;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
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
            return ResponseEntity.status(400)
                    .body("city not found");

        return ResponseEntity.status(200)
                .body(opt.get().toString());
    }

    @PostMapping(path = "/cities/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> addCity(@RequestBody Map<String, String> payload) throws IOException {

        String city = payload.get("city");

        // check if I can get city by calling api
        Optional<JsonObject> opt = service.getWeather(city);

        // JsonObjectBuilder json = Json.createObjectBuilder();
        Map<String, String> response = new HashMap<>();

        // if not found, return 400
        if (opt.isEmpty()) {
        
            response.put("message", "invalid city");
            // json.add("message", "invalid city cannot be added").build();
           
            return ResponseEntity.status(400)
                    .body(response);
            // .body(Json.createObjectBuilder().add("message", "cannot add invalid city").build().toString());
        }

        // add to SQL if it doesn't already exist in database
        Integer citiesAdded = service.addCity(city);

        if (citiesAdded <= 0) {
            response.put("message", "city cannot be added");
            return ResponseEntity.status(400)
                    .body(response);
        }

        response.put("message", "city added");
        // json.add("message", "city added").build();
        
        return ResponseEntity.status(200)
                .body(response);
        // .body(Json.createObjectBuilder().add("message", "city added").build().toString());
    }
}
