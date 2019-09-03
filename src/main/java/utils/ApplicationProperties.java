package utils;

import java.io.IOException;
import java.util.Properties;

public enum ApplicationProperties {
    INSTANCE;

    private final Properties properties;

    ApplicationProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    public int getPort() {
        return Integer.parseInt(properties.getProperty("port"));
    }

    public String getReqresBaseUrl() {
        return properties.getProperty("reqresBaseUrl");
    }
}
