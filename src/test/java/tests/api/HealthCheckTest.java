package tests.api;


import commonLibs.api.HealthCheckAPI;
import org.testng.annotations.Test;



public class HealthCheckTest extends BaseTest {

    HealthCheckAPI restapi=new HealthCheckAPI();
    @Test(description = "Test Get healthcheck of service.")
    public void getHealthCheckTest() {
        restapi.getHealthCheck();
    }




}
