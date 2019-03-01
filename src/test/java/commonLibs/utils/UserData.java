package commonLibs.utils;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class UserData {
    CommonFunc commonFunc=new CommonFunc();
    public Map<String, String> user(){
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        Map<String, String> list = new HashMap<String, String>();
        list.put("email", firstName+"@gmail.com");
        list.put("firstName", firstName);
        list.put("lastName", lastName);
        list.put("id", "3");
        return list;
    }
    public Map<String, String> userChangeData(String data){
        Map<String,String> userDetails=new HashMap<String, String>();
        String [][] array= commonFunc.stringParser(data);
        System.out.println(array.length);
        for (int i=0;i<array.length;i++){
            userDetails.put(array[i][0], array[i][1]);
        }
        return userDetails;
    }
}
