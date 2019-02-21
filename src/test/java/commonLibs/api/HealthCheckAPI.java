package commonLibs.api;
import commonLibs.configReaders.ConfigReader;
import org.hamcrest.Matchers;


import static io.restassured.RestAssured.given;

public class HealthCheckAPI extends BaseAPI{


    public void getHealthCheck(){
        ConfigReader reader = new ConfigReader();

        String site = reader.getProp("QA_env", "baseUrl");
        String path = reader.getProp("Common", "helthCheckPath");

        given()
                .get(site+path)
                .then()
                .body(  Matchers.equalTo("live"));

    }

}
