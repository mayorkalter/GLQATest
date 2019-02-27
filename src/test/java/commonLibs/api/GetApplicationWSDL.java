package commonLibs.api;


import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class GetApplicationWSDL extends BaseAPI{

    public Response getApplication() {
        Response res=given().get(applicationUrl);
        return res;
    }
}
