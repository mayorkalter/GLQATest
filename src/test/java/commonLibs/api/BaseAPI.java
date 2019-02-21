package commonLibs.api;

import commonLibs.configReaders.ConfigReader;
import commonLibs.utils.CommonFunc;
import io.restassured.internal.assertion.Assertion;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseAPI {

    CommonFunc commonFunc=new CommonFunc();



    public Response contactIdGet(int id) {

        ConfigReader reader = new ConfigReader();

        String site = reader.getProp("QA_env", "baseUrl");
        String path = reader.getProp("Common", "contactsPath");

        Response res=given()
                .get(site+path+"/"+id);
        int statusCode = res.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        commonFunc.checkUrl(res,id);
        return res;
    }



    public void contactIdPut(int id, String firstName,String lastName, String email) {
        ConfigReader reader = new ConfigReader();

        String site = reader.getProp("QA_env", "baseUrl");
        String path = reader.getProp("Common", "contactsPath");


        Map<String, String> userDetails = new HashMap<String, String>();
        userDetails.put("firstName", firstName);
        userDetails.put("lastName", lastName);
        userDetails.put("email", email);

        Response res = given().header("Content-Type", "application/json").
                body(userDetails).put(site+path+"/"+id);
        String body = res.getBody().asString();
        int statusCode = res.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        commonFunc.checkUserInfo(res, firstName, lastName, email);
        commonFunc.checkUrl(res,id);
    }

    public void contactIdPost(String firstName,String lastName, String email) {
        ConfigReader reader = new ConfigReader();

        String site = reader.getProp("QA_env", "baseUrl");
        String path = reader.getProp("Common", "contactsPath");

        Map<String, String> userDetails = new HashMap<String, String>();
        userDetails.put("firstName", firstName);
        userDetails.put("lastName", lastName);
        userDetails.put("email", email);

        Response res = given().header("Content-Type", "application/json").
                body(userDetails).post(site+path);

        int statusCode = res.getStatusCode();
        Assert.assertEquals(statusCode, 201);
        int id=Integer.parseInt(res.jsonPath().get("data.id[0]").toString());
        commonFunc.checkUserInfo(res, firstName, lastName, email);
        commonFunc.checkUrl(res,id);
    }



    public void contactIdPatch(int id, String key,String value) {

        ConfigReader reader = new ConfigReader();

        String site = reader.getProp("QA_env", "baseUrl");
        String path = reader.getProp("Common", "contactsPath");

        Map<String, String> userDetails = new HashMap<String, String>();
        userDetails.put(key, value);


        Response res = given().header("Content-Type", "application/json").
                body(userDetails).patch(site+path+"/"+id);
        String body = res.getBody().asString();
        int statusCode = res.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        String Avalue = res.jsonPath().get("data.info[0]."+key);
        Assert.assertEquals(Avalue, value);
        commonFunc.checkUrl(res,id);

    }



    public void contactIdDelete(int id) {
        ConfigReader reader = new ConfigReader();

        String site = reader.getProp("QA_env", "baseUrl");
        String path = reader.getProp("Common", "contactsPath");

        Response res = given().delete(site+path+"/"+id);
        int statusCode = res.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }


    public void applicationGet() {
        ConfigReader reader = new ConfigReader();

        String site = reader.getProp("QA_env", "baseUrl");
        String path = reader.getProp("Common", "application");

        Response res=given().get(site+path);
        int statusCode = res.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }


    public void contactsGet() {
        ConfigReader reader = new ConfigReader();

        String site = reader.getProp("QA_env", "baseUrl");
        String path = reader.getProp("Common", "contactsPath");

        given().get(site+path).then().body("data.id[0]",  Matchers.equalTo(1),
                        "data.info[0].email",  Matchers.equalTo("john.doe@unknown.com"),
                        "data.info[0].firstName", Matchers.equalTo("John"),
                        "data.info[0].lastName",  Matchers.equalTo("Doe"),
                        "data.refs[0].patch",  Matchers.equalTo("http://host:port/api/v1/contacts/1"),
                        "data.refs[0].get",  Matchers.equalTo("http://host:port/api/v1/contacts/1"),
                        "data.refs[0].delete",  Matchers.equalTo("http://host:port/api/v1/contacts/1"),
                        "data.refs[0].put",  Matchers.equalTo("http://host:port/api/v1/contacts/1"),
                        "status",  Matchers.equalTo(200));
    }


    public void contactGetFind(String fs) {
        ConfigReader reader = new ConfigReader();

        String site = reader.getProp("QA_env", "baseUrl");
        String path = reader.getProp("Common", "contactsPath");

        String[][] keyvalue=commonFunc.stringParser(fs);

        Response resp=given().get(site+path+"?"+fs);

        int arrlength=keyvalue.length;
        for (int i=0;i<arrlength;i++){
            String val = resp.jsonPath().get("data.info[0]."+keyvalue[i][0]);
            val.contains(keyvalue[i][1]);
        }
        int statusCode = resp.getStatusCode();
        Assert.assertEquals(statusCode, 200);


    }


    /*public String[][] stringParser(String fs) {

        int count = 0;
        for(int i=0; i < fs.length(); i++)
        {    if(fs.charAt(i) == '=')
            count++;
        }
        String[][] array=new String[2][count];
        for (int i=0; i<count;i++) {
            String key = fs.split("=")[0];
            String value;
            int lastindex = fs.indexOf('&');
            if(lastindex==-1){
                value = fs.substring(fs.indexOf('=') + 1);
            }else {

                value = fs.substring(fs.indexOf('=') + 1, fs.indexOf('&'));
            }
            fs=fs.substring(fs.indexOf('&') + 1);

            array[i][0] = key;
            array[i][1] = value;
        }

        return array;
    }

    public void checkUrl(Response res, int id){
        String Apatch = res.jsonPath().get("data.refs[0].patch");
        Assert.assertEquals(Apatch, "http://host:port/api/v1/contacts/"+id);
        String Aget = res.jsonPath().get("data.refs[0].get");
        Assert.assertEquals(Aget, "http://host:port/api/v1/contacts/"+id);
        String Adelete = res.jsonPath().get("data.refs[0].delete");
        Assert.assertEquals(Adelete, "http://host:port/api/v1/contacts/"+id);
        String Aput = res.jsonPath().get("data.refs[0].put");
        Assert.assertEquals(Aput, "http://host:port/api/v1/contacts/"+id);

    }
    public void checkUserInfo(Response res, String firstName, String lastName, String email){
        String AfirstName = res.jsonPath().get("data.info[0].firstName");
        Assert.assertEquals(AfirstName, firstName);
        String AlastName = res.jsonPath().get("data.info[0].lastName");
        Assert.assertEquals(AlastName, lastName);
        String Aemail = res.jsonPath().get("data.info[0].email");
        Assert.assertEquals(Aemail, email);
    }*/

}
