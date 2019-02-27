package tests.api.contacts;

import commonLibs.api.GetContactsAPI;
import commonLibs.utils.CommonFunc;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import tests.api.BaseTest;

public class GetContactsTest extends BaseTest {

    GetContactsAPI restapi=new GetContactsAPI();
    CommonFunc commonFunc=new CommonFunc();


    @Test(description = "Test Get contacts all endpoint.")
    public void getContactsTest(){
        Response res=restapi.getContacts();
        commonFunc.checkStatusCode(res, 200);
    }


    @Test(description = "Test Get contacts by string endpoint.")
    public void getFindContactTest(){
        restapi.getFindContact("firstName=John&email=.*unknown.com");
    }

    @Test(description = "Test Get contacts by ID endpoint.")
    public void getContactIdTest() {
        int id =2;
        Response res=restapi.getContactId(id);
        commonFunc.checkStatusCode(res, 200);
    }

}
