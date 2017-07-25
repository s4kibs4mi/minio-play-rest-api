package configs;

import java.util.UUID;

/**
 * := Coded with love by Sakib Sami on 7/25/17.
 * := s4kibs4mi@gmail.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

public class MinioConfig {
    public static String getMsHost() {
        return "http://188.166.242.24";
    }

    public static String getMsUri() {
        return "http://188.166.242.24:9000";
    }

    public static String getMsSecret() {
        return "ByySJw6Pan26T96YW+ptBxls6cMyeRbYq9XxSlhL";
    }

    public static String getMsAccessKey() {
        return "1C9RWK7AAPXPHH2ZS2IC";
    }

    public static String getRandomText() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
