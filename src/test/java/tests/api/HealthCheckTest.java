package tests.api;


import commonLibs.api.HealthCheckAPI;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import commonLibs.utils.Logger;


@Listeners(Logger.class)
public class HealthCheckTest extends BaseTest {

    HealthCheckAPI restapi=new HealthCheckAPI();
    @Test(description = "Test Get healthcheck of service.")
    public void applicationGet() {
        restapi.getHealthCheck();
    }




}
