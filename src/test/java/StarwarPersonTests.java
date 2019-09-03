
import Models.People;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.path.json.JsonPath;

import org.apache.http.HttpStatus;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class StarwarPersonTests extends BaseTest {

    private Logger logger =  Logger.getLogger(StarwarPersonTests.class);


    @Test
    public void getPeopleTest() {
        when().
                request("GET","/people/1").
                then().
                statusCode(200);
    }

    @Test
    public void getPeopleAsJson() throws JsonProcessingException {
        JsonPath jsonPath = when().
                request("GET","/people/1").
                then().
                extract().body().jsonPath();

        String name = jsonPath.getString("name");

        assertThat(name,is(notNullValue()));

    }

    @Test
    public void getPeopleAsModel() throws JsonProcessingException {
        People people = when().
                request("GET","/people/1").
                then().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                extract().
                as(People.class);

        String name = people.getName();

        assertThat(name,is(notNullValue()));
        assertThat(name,containsString("Luke Skywalker"));
        //more asserts here

    }
}
