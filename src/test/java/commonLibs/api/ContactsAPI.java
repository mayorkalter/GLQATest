package commonLibs.api;

import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;

public class ContactsAPI extends BaseAPI {

    public void checkUserInfo(Response res, Map<String,String> userDetails){
        for (int i=0; i<userDetails.size();i++){
            Map.Entry<String,String> entry = userDetails.entrySet().iterator().next();
            String key= entry.getKey();
            System.out.println(key);
            String Evalue=entry.getValue();
            System.out.println(Evalue);
            String Avalue = res.jsonPath().get("data.info[0]."+key);
            Assert.assertEquals(Avalue, Evalue);
        }
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
}
