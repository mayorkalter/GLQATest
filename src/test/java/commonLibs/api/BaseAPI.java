package commonLibs.api;

import commonLibs.configReaders.PathCreater;
import commonLibs.utils.CommonFunc;
import commonLibs.utils.UserData;
import io.restassured.response.Response;
import org.testng.Assert;

public class BaseAPI {
    CommonFunc commonFunc=new CommonFunc();
    UserData userData=new UserData();
    PathCreater path=new PathCreater();
    String contactsUrl=path.getUrl("contactsPath");
    String applicationUrl=path.getUrl("application");
    String healthCheckUrl=path.getUrl("helthCheckPath");

}
