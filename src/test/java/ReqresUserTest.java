
import Models.TestUser;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.when;

public class ReqresUserTest extends ReqresBaseTest {
    private Logger logger =  Logger.getLogger(ReqresUserTest.class);

    @Test
    public void getSingleUserTest() {
        JsonPath jsonPath =  when()
                .request("GET","/api/users/1")
                .then()
                .statusCode(200).extract().body().jsonPath();
        TestUser data = jsonPath.getObject("data",TestUser.class);
        System.out.println(data.getEmail());
    }

    @Test
    public void createUserTestAsJson() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\": \"Lisa\",\"job\": \"engg\"}")
                .when()
                .post( "/api/users");
        System.out.println("POST Response\n" + response.asString());
        // tests
        response.then().body("id", Matchers.any(String.class));
        response.then().body("name", Matchers.is("Lisa"));
    }
}
