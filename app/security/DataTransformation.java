package security;

import com.eclipsesource.json.JsonArray;

import java.util.List;

/**
 * := Coded with love by Sakib Sami on 8/7/17.
 * := minio_play_rest_api
 * := root@sakib.ninja
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

public class DataTransformation {
    public static JsonArray toJsonArray(List<String> list) {
        JsonArray values = new JsonArray();
        for (String item : list) {
            values.add(item);
        }
        return values;
    }
}
