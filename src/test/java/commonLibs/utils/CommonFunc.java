package commonLibs.utils;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CommonFunc {

    public String[][] stringParser(String fs) {

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
    public void checkUserInfo(Response res, Map<String,String>userDetails){
        for (int i=0; i<userDetails.size();i++){
            Map.Entry<String,String> entry = userDetails.entrySet().iterator().next();
            String key= entry.getKey();
            String Evalue=entry.getValue();
            String Avalue = res.jsonPath().get("data.info[0]."+key);
            Assert.assertEquals(Avalue, Evalue);
        }
        /*String AfirstName = res.jsonPath().get("data.info[0].firstName");
        Assert.assertEquals(AfirstName, user().get("firstName") );
        String AlastName = res.jsonPath().get("data.info[0].lastName");
        Assert.assertEquals(AlastName, user().get("lastName"));
        String Aemail = res.jsonPath().get("data.info[0].email");
        Assert.assertEquals(Aemail, user().get("email"));*/
    }

    public void checkStatusCode(Response res, int status){
        int statusCode = res.getStatusCode();
        Assert.assertEquals(statusCode, status);
    }

    public Map<String, String> user(){
        Map<String, String> list = new HashMap<String, String>();
        list.put("email", "fen@baldr.hel");
        list.put("firstName", "Sol");
        list.put("lastName", "Fenrir");
        list.put("id", "2");
        return list;
    }
    public Map<String, String> userChangeData(String data){
        Map<String,String> userDetails=new HashMap<String, String>();
        String [][] array= stringParser(data);
        for (int i=0;i<array.length;i++){
            userDetails.put(array[i][0], array[i][1]);
        }
        return userDetails;
    }


    public RequestSpecification request(Map<String,String> userDetails){
        RequestSpecification req = given().header("Content-Type", "application/json").body(userDetails);
        return req;
    }

}
