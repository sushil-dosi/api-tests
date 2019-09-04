
import Models.TestUser;
import Models.UserModel;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import static org.hamcrest.Matchers.*;

import static org.hamcrest.MatcherAssert.assertThat;


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
        assertThat(data.getEmail(),equals("george.bluth@reqres.in"));
        assertThat(data.getId(),equals("2"));

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

    @Test
    public void updateUserTestAsJson() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\": \"Lisa\",\"job\": \"engg\"}")
                .when()
                .put( "/api/users/2");
        System.out.println("PUT Response\n" + response.asString());
        // tests
        response.then().body("name", Matchers.is("Lisa"));
    }

    @Test
    public void deleteUserTest() {
        when()
                .request("DELETE","/api/users/1")
                .then()
                .statusCode(204).extract().body().jsonPath();
    }

    @Test
    public void createUserTestAsModel() {
        UserModel userModel = new UserModel();
        userModel.setJob("Eng");
        userModel.setName("Atishay");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userModel)
                .when()
                .post( "/api/users");
        System.out.println("POST Response\n" + response.asString());
        // tests
        response.then().body("id", Matchers.any(String.class));
        response.then().body("name", Matchers.is("Atishay"));
    }
}
