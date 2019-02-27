package commonLibs.api;

import commonLibs.utils.CommonFunc;
import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class ChangeContactsAPI extends BaseAPI {
    CommonFunc commonFunc=new CommonFunc();
    public Response putContactId(Map<String,String> userDetails) {
        int id=Integer.parseInt(userDetails.get("id"));
        System.out.println(id);
        Response res=commonFunc.request(userDetails).put(contactsUrl+"/"+id);
        commonFunc.checkUserInfo(res, userDetails);
        commonFunc.checkUrl(res,id);
        return res;
    }

    public Response postContactId(Map<String,String> userDetails) {
        Response res=commonFunc.request(userDetails).post(contactsUrl);
        int id=Integer.parseInt(res.jsonPath().get("data.id[0]").toString());
        commonFunc.checkUserInfo(res, userDetails);
        commonFunc.checkUrl(res,id);
        return res;
    }



    public Response patchContactId(Map<String,String> userDetails) {

        int id=Integer.parseInt(userDetails.get("id"));
        Response res=commonFunc.request(userDetails).patch(contactsUrl+"/"+id);
        commonFunc.checkUserInfo(res,userDetails);
        commonFunc.checkUrl(res,id);
        return res;
    }

    public void deleteContactId(int id) {

        Response res = given().delete(contactsUrl+"/"+id);
        commonFunc.checkStatusCode(res,200);
    }
}
