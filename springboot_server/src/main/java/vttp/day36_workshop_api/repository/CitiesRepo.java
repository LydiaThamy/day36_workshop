package vttp.day36_workshop_api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CitiesRepo {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public final static String GET_CITIES_SQL = "select distinct city from cities";
    public final static String ADD_CITY_SQL = "insert into cities values (null, ?)";

    public List<String> getCities() {
        return jdbcTemplate.queryForList(GET_CITIES_SQL, String.class);
    }

    public Integer addCity(String city) {
        return jdbcTemplate.update(ADD_CITY_SQL, city);
    }

}
