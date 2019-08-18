package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

public class PropertyReader {

    public static String baseURL = getProperty("baseUrl");
    public static String browserType = getProperty("browserType");


    /**
     * Gets the property based on the given property file.
     * @param property Property name as written in property file.
     * @return value of the property.
     */
    private static String getProperty(String property) {
        try {
            return readProperties().getProperty(property);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }

    /**
     * Gets the file path and populates Properties object with the file.
     * @return property object.
     * @throws IOException
     */


    private static Properties readProperties() throws IOException {
        Path filePath = Utils.getResourcePath("config/PROD.properties");
        InputStream input = new FileInputStream(filePath.toString());
        Properties prop = new Properties();
        prop.load(input);
        return prop;
    }

}


