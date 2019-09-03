import Models.Planet;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.util.List;

import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SwapiPlanetTest extends BaseTest {
    private Logger logger =  Logger.getLogger(SwapiPlanetTest.class);

    @Test
    public void getPlanetTest() {
        when().
                request("GET","/planets/1").
                then().
                statusCode(200);
    }

    @Test
    public void getPlanetAsJson() throws JsonProcessingException {
        JsonPath jsonPath = when().
                request("GET","/planets/1").
                then().
                extract().body().jsonPath();

        String name = jsonPath.getString("name");
        String climate = jsonPath.getString("climate");
        List<String> residents = jsonPath.getList("residents");
        assertThat(name,is(notNullValue()));
        assertThat(name,containsString("Tatooine"));
        assertThat(climate,containsString("arid"));
        assertThat(residents,notNullValue());
        assertThat(residents,not(empty()));
    }

    @Test
    public void getPlanetAsModel() throws JsonProcessingException {
        Planet planet = when().
                request("GET","/planets/1").
                then().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                extract().
                as(Planet.class);

        String name = planet.getName();
        String climate = planet.getClimate();
        List<String> residents = planet.getResidents();

        assertThat(name,is(notNullValue()));
        assertThat(name,containsString("Tatooine"));
        assertThat(climate,containsString("arid"));
        assertThat(residents,notNullValue());
        assertThat(residents,not(empty()));
    }
}
