package stas.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by stas on 1/23/17.
 */
public class PropertyLoader  {

    public static String getProperty(String property) {
        if (property == null) {
            throw new NullPointerException("property is null");
        }
        Properties properties = new Properties();
        try (InputStream is = PropertyLoader.class.getClassLoader()
                .getResourceAsStream("Data.properties")) {
            properties.load(is);
        }
        catch (IOException io) {
            io.printStackTrace();
        }
        return properties.getProperty(property);
    }
}
