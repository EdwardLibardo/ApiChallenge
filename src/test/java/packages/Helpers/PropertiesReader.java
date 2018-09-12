package packages.Helpers;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    static Properties properties;
    public PropertiesReader() {
        try {
            properties = new Properties();
            FileReader fileReader = new FileReader("src/test/resources/config.properties");
            properties.load(fileReader);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getMenu() {
        return properties.getProperty("mainEndpoint");
    }


    public String getTopFive() {
        return properties.getProperty("topFive");
    }

    public String getid() {
        return properties.getProperty("id");
    }
}
