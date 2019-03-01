package tests.api;

import commonLibs.api.GetApplicationWSDL;
import commonLibs.utils.CommonFunc;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetApplication extends BaseTest {

    GetApplicationWSDL restapi=new GetApplicationWSDL();
    @Test(description = "Test Get application.wsdl endpoint.")
    public void getApplicationTest() {
        Response res = restapi.getApplication();
        commonFunc.checkStatusCode(res, 200);
    }

}
