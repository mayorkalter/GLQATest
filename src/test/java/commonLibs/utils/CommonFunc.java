package commonLibs.utils;

import com.github.javafaker.Faker;
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
        System.out.println(count);
        String[][] array=new String[count][2];
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


//    public void checkStatusCode(Response res, int status){
//        int statusCode = res.getStatusCode();
//        Assert.assertEquals(statusCode, status);
//    }
//
//    public RequestSpecification request(Map<String,String> userDetails){
//        RequestSpecification req = given().header("Content-Type", "application/json").body(userDetails);
//        return req;
//    }

}
