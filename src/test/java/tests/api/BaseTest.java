package tests.api;

import commonLibs.api.BaseAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import commonLibs.utils.Logger;


@Listeners(Logger.class)
public class BaseTest {
    BaseAPI restapi=new BaseAPI();

    @Test(description = "Test Get application.wsdl endpoint.")
    public void applicationGetTest() {

        restapi.applicationGet();
    }

    @Test(description = "Test Get contacts all endpoint.")
    public void contactsGetTest(){
        restapi.contactsGet();
    }


    @Test(description = "Test Get contacts by string endpoint.")
    public void contactGetFindTest(){
        restapi.contactGetFind("firstName=John&email=.*unknown.com");
    }

    @Test(description = "Test Get contacts by ID endpoint.")
    public void contactIdGetTest() {

        Response res=restapi.contactIdGet(1);

        String AfirstName = res.jsonPath().get("data.info[0].firstName");
        Assert.assertEquals(AfirstName, "John");
        String AlastName = res.jsonPath().get("data.info[0].lastName");
        Assert.assertEquals(AlastName, "Doe");
        String Aemail = res.jsonPath().get("data.info[0].email");
        Assert.assertEquals(Aemail, "john.doe@unknown.com");
    }

    @Test(description = "Test Post contacts ID endpoint.")
    public void contactIdPostTest() {

        restapi.contactIdPost("Bobby", "Emilzy","bobby@gg.com");
    }

    @Test(description = "Test PUT contacts ID endpoint.")
    public void contactIdPut() {

        restapi.contactIdPut(2,"Bobby", "Emilzy","bobby@gg.com");
    }


    @Test(description = "Test Patch contacts ID endpoint.")
    public void contactIdPatchTest() {

        restapi.contactIdPatch(2,"firstName", "Merlin");
    }

    @Test(description = "Test delete contacts ID endpoint.")
    public void contactIdDeleteTest() {

        restapi.contactIdDelete(15);
    }

}
