package configs;

import play.Configuration;
import play.Environment;

import java.util.UUID;

/**
 * := Coded with love by Sakib Sami on 7/25/17.
 * := s4kibs4mi@gmail.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

public class MinioConfig {
    private static Configuration configuration = Configuration.load(Environment.simple());

    public static String getMsHost() {
        return configuration.getString("minioConfig.msHost");
    }

    public static String getMsUri() {
        return configuration.getString("minioConfig.msUri");
    }

    public static String getMsSecret() {
        return configuration.getString("minioConfig.msSecrete");
    }

    public static String getMsAccessKey() {
        return configuration.getString("minioConfig.msAccessToken");
    }

    public static String getRandomText() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
