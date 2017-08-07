package security;

import com.fasterxml.jackson.databind.JsonNode;
import play.data.validation.Constraints;

public class DataValidation {
    private static Constraints.EmailValidator emailValidator = new Constraints.EmailValidator();

    public static String filterForSqlInjection(String value) {
        return value.replace("'", "")
                .replaceAll("\"", "")
                .replaceAll("=", "");
    }

    public static boolean isNotNull(Object value) {
        return value != null;
    }

    public static boolean isNull(Object value) {
        return value == null;
    }

    public static boolean isValid(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isNotEmpty(String value) {
        return !value.isEmpty();
    }

    public static boolean isEmpty(String value) {
        return value.isEmpty();
    }

    public static boolean isEmail(String value) {
        return emailValidator.isValid(value);
    }

    public static String getParam(JsonNode value, String key) {
        JsonNode it = value.get(key);
        if (isNotNull(it)) {
            String param = filterForSqlInjection(it.asText());
            return param.trim().isEmpty() ? null : param.trim();
        }
        return null;
    }
}
