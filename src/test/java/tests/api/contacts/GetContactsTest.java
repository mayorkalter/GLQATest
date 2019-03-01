package tests.api.contacts;

import commonLibs.api.ContactsAPI;
import commonLibs.api.GetContactsAPI;
import commonLibs.utils.CommonFunc;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import tests.api.BaseTest;

public class GetContactsTest extends BaseTest {

    GetContactsAPI restapi=new GetContactsAPI();
    ContactsAPI cntactsApi=new ContactsAPI();


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
//        Map<String,String> userData=commonFunc.user();
//        userData.put("firstName","Sol");
//        userData.put("lastName","Fenrir");
//        userData.put("email","fen@baldr.hel");
        commonFunc.checkStatusCode(res, 200);
        cntactsApi.checkUserInfo(res, userData.userChangeData("id=2&firstName=Ymir&lastName=Fenrir&email=fen@baldr.hel"));
    }

}
