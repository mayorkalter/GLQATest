package commonLibs.api;

import commonLibs.configReaders.PathCreater;

public class BaseAPI {
    PathCreater path=new PathCreater();
    String contactsUrl=path.getUrl("contactsPath");
    String applicationUrl=path.getUrl("application");
    String healthCheckUrl=path.getUrl("helthCheckPath");



}
