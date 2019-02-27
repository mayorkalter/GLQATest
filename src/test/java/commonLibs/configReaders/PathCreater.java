package commonLibs.configReaders;

public class PathCreater {
    ConfigReader reader = new ConfigReader();
    public String getUrl(String path){
        String site = reader.getProp("QA_env", "baseUrl");
        String endpointPath = reader.getProp("Common", path);
        String endpointUrl=site+endpointPath;
        return endpointUrl;
    }
}
