package commonLibs.api;
import commonLibs.configReaders.ConfigReader;
import org.hamcrest.Matchers;


import static io.restassured.RestAssured.given;

public class HealthCheckAPI extends BaseAPI{

    public void getHealthCheck(){

        given()
                .get(healthCheckUrl)
                .then()
                .body(  Matchers.equalTo("live"));

    }

}
