package vttp.day36_workshop_api.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.day36_workshop_api.model.Weather;
import vttp.day36_workshop_api.repository.CitiesRepo;
import vttp.day36_workshop_api.repository.WeatherRepo;

@Service
public class WeatherService {

    @Autowired
    private CitiesRepo cityRepo;

    @Autowired
    private WeatherRepo weatherRepo;

    public JsonArray getCities() {
        JsonArrayBuilder jBuilder = Json.createArrayBuilder();

        for (String city : cityRepo.getCities()) {
            jBuilder.add(city);
        }

        return jBuilder.build();
    }

    public Optional<JsonObject> getWeather(String city) throws IOException {

        Optional<Weather> opt = weatherRepo.getWeather(city);

        if (opt.isEmpty())
            return Optional.empty();

        Weather w = opt.get();

        JsonObject json = Json.createObjectBuilder()
                .add("description", w.getDescription())
                .add("temperature", String.format("%.1f", w.getTemperature()) + "°C")
                .add("visibility", String.format("%.1f", w.getVisibility()) + "km")
                .build();

        return Optional.of(json);
        // return Optional.of(opt.get());
    }

    public Integer addCity(String city) {
        return cityRepo.addCity(city);
    }
}
