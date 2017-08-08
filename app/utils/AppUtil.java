package utils;

import org.joda.time.DateTime;
import play.Logger;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * := Coded with love by Sakib Sami on 8/5/17.
 * := minio_play_rest_api
 * := root@sakib.ninja
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

public class AppUtil {
    public static int SESSION_LENGTH_IN_HOURS = 48;

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static Date getCurrentDate() {
        return DateTime.now().toDate();
    }

    public static void logDebug(Object scope, String message) {
        Logger.debug(scope.getClass().getSimpleName() + " := " + message);
    }

    public static void logInfo(Object scope, String message) {
        Logger.info(scope.getClass().getSimpleName() + " := " + message);
    }

    public static void logError(Object scope, String message) {
        Logger.error(scope.getClass().getSimpleName() + " := " + message);
    }
}
