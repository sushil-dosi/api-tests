import Models.People;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ApplicationProperties;

import org.testng.log4testng.Logger;

import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class BaseTest {

    private Logger logger =  Logger.getLogger(BaseTest.class);

    @BeforeSuite
    public void beforeTest() {
        logger.info("Initializing Suite");
        RestAssured.baseURI = ApplicationProperties.INSTANCE.getBaseUrl();
        RestAssured.port = ApplicationProperties.INSTANCE.getPort();
    }

    @AfterSuite
    public void afterSuite() {
        logger.info("Terminating Suite");
    }



}
