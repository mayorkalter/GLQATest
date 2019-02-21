package commonLibs.utils;

import io.restassured.response.Response;
import org.testng.Assert;

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
    public void checkUserInfo(Response res, String firstName, String lastName, String email){
        String AfirstName = res.jsonPath().get("data.info[0].firstName");
        Assert.assertEquals(AfirstName, firstName);
        String AlastName = res.jsonPath().get("data.info[0].lastName");
        Assert.assertEquals(AlastName, lastName);
        String Aemail = res.jsonPath().get("data.info[0].email");
        Assert.assertEquals(Aemail, email);
    }

}
