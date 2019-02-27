package tests.api.contacts;

import commonLibs.api.ChangeContactsAPI;
import commonLibs.utils.CommonFunc;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import tests.api.BaseTest;


public class ChangeContacts extends BaseTest {
    ChangeContactsAPI restapi=new ChangeContactsAPI();
    CommonFunc commonFunc=new CommonFunc();
    @Test(description = "Test Post contacts ID endpoint.")
    public void postContactIdTest() {
        Response res=restapi.postContactId(commonFunc.user());
        commonFunc.checkStatusCode(res, 201);
    }

    @Test(description = "Test PUT contacts ID endpoint.")
    public void putContactId() {

        Response res=restapi.putContactId(commonFunc.user());
        commonFunc.checkStatusCode(res, 200);

    }


    @Test(description = "Test Patch contacts ID endpoint.")
    public void patchContactIdTest() {

        Response res=restapi.patchContactId(commonFunc.userChangeData("id=2&firstName=Ymir"));
        commonFunc.checkStatusCode(res, 200);

    }

    @Test(description = "Test delete contacts ID endpoint.")
    public void deleteContactIdTest() {

        restapi.deleteContactId(18);
    }
}
