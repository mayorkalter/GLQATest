package commonLibs.configReaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);

    public String getProp(String nameFileProperties, String nameProp) {
        Properties properties = new Properties();

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("config/" + nameFileProperties + ".properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            logger.error("File not exist");
            e.printStackTrace();
        }

        return properties.getProperty(nameProp);
    }

}
