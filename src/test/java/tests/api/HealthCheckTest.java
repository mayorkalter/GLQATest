package tests.api;

import commonLibs.configReaders.ConfigReader;
import io.restassured.response.Response;
import org.json.JSONException;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.get;


public class HealthCheckTest extends BaseTest {
    @Test
    public void getRequestFindCapital() throws JSONException {
        ConfigReader reader = new ConfigReader();

        String site = reader.getProp("QA_env", "baseUrl");
        String path = reader.getProp("Common", "helthCheckPath");

        //Response resp = get("http://127.0.0.1:8080/healthcheck");
        Response resp = get(site + path);
        System.out.println(resp.asString());
        AssertJUnit.assertEquals(resp.asString(), "live");

    }
}
