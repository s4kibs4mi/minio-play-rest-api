package validators;

import java.util.ArrayList;

/**
 * := Coded with love by Sakib Sami on 8/6/17.
 * := minio_play_rest_api
 * := root@sakib.ninja
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

public interface IValidator {
    void validate();

    Object get();

    boolean hasErrors();

    ArrayList<String> getErrors();
}
