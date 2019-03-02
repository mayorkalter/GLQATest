package tests.api;

import commonLibs.api.BaseAPI;
import commonLibs.utils.CommonFunc;
import commonLibs.utils.Logger;
import commonLibs.utils.UserData;
import org.testng.annotations.Listeners;


@Listeners(Logger.class)
public class BaseTest {
    public CommonFunc commonFunc = new CommonFunc();
    public UserData userData = new UserData();
    public BaseAPI baseAPI=new BaseAPI();
}
