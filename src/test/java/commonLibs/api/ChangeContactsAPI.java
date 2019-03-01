package commonLibs.api;

import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class ChangeContactsAPI extends ContactsAPI {
    public Response putContactId(Map<String,String> userDetails) {
        int id=Integer.parseInt(userDetails.get("id"));
        Response res=commonFunc.request(userDetails).put(contactsUrl+"/"+id);
        checkUserInfo(res, userDetails);
        checkUrl(res,id);
        return res;
    }

    public Response postContactId(Map<String,String> userDetails) {
        Response res=commonFunc.request(userDetails).post(contactsUrl);
        int id=Integer.parseInt(res.jsonPath().get("data.id[0]").toString());
        checkUserInfo(res, userDetails);
        checkUrl(res,id);
        return res;
    }



    public Response patchContactId(Map<String,String> userDetails) {

        int id=Integer.parseInt(userDetails.get("id"));
        Response res=commonFunc.request(userDetails).patch(contactsUrl+"/"+id);
        checkUserInfo(res,userDetails);
        checkUrl(res,id);
        return res;
    }

    public void deleteContactId(int id) {

        Response res = given().delete(contactsUrl+"/"+id);
        commonFunc.checkStatusCode(res,200);
    }
}
