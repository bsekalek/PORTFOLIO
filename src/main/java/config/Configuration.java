package config;

import lombok.SneakyThrows;
import java.util.Properties;

public class Configuration {

    private static Properties instance = null;

    @SneakyThrows
    private static Properties getInstance() {
        if (instance == null) {
            instance = new Properties();
            instance.load(Configuration.class.getClassLoader().getResourceAsStream("config.properties"));
        }
        return instance;
    }

    public static String getUrl(){
        return getInstance().get("url").toString();
    }

    public static String getUsername(){
        return getInstance().get("username").toString();
    }
}
