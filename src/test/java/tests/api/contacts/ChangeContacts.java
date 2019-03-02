package tests.api.contacts;

import commonLibs.api.BaseAPI;
import commonLibs.api.ChangeContactsAPI;
import commonLibs.utils.CommonFunc;
import commonLibs.utils.UserData;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import tests.api.BaseTest;


public class ChangeContacts extends BaseTest {
    ChangeContactsAPI restapi=new ChangeContactsAPI();
    @Test(description = "Test Post contacts ID endpoint.")
    public void postContactIdTest() {
        Response res=restapi.postContactId(userData.user());
        baseAPI.checkStatusCode(res, 201);
    }

    @Test(description = "Test PUT contacts ID endpoint.")
    public void putContactId() {

        Response res=restapi.putContactId(userData.user());
        baseAPI.checkStatusCode(res, 200);

    }


    @Test(description = "Test Patch contacts ID endpoint.")
    public void patchContactIdTest() {

        Response res=restapi.patchContactId(userData.userChangeData("id=2&firstName=Ymir"));
        baseAPI.checkStatusCode(res, 200);

    }

    @Test(description = "Test delete contacts ID endpoint.")
    public void deleteContactIdTest() {

        restapi.deleteContactId(19);
    }
}
