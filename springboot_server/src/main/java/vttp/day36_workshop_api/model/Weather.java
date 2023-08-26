package vttp.day36_workshop_api.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    String description;
    Double temperature;
    Double visibility;

    public static Weather create(String resp) throws IOException {
        
        // create JSON reader
        JsonReader reader = Json.createReader(
                new ByteArrayInputStream(
                    resp.getBytes())); 

        JsonObject json = reader.readObject();
        
        Weather w = new Weather();

        // weather
        JsonObject weather = json.getJsonArray("weather")
            .getJsonObject(0);
        w.setDescription(weather.getString("description"));

        // temperature
        JsonObject main = json.getJsonObject("main");
        w.setTemperature(main.getJsonNumber("temp").doubleValue() - 273.15);

        // visibility
        w.setVisibility(json.getJsonNumber("visibility").doubleValue() / 1000);

        return w;
    }
    
}
