package validators;

import com.fasterxml.jackson.databind.JsonNode;
import configs.ParamConfig;
import models.User;
import security.DataValidation;
import utils.CryptoUtil;

import java.util.ArrayList;

/**
 * := Coded with love by Sakib Sami on 8/7/17.
 * := minio_play_rest_api
 * := root@sakib.ninja
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

public class AuthValidator implements IValidator {
    private JsonNode params;
    private ArrayList<String> errors;
    private User user;

    public AuthValidator(JsonNode params) {
        this.params = params;
        this.errors = new ArrayList<>();
        this.user = new User();
    }

    @Override
    public void validate() {
        String userEmail = DataValidation.getParam(params, ParamConfig.PARAM_USER_EMAIL_KEY);
        if (DataValidation.isNull(userEmail)) {
            errors.add("Invalid email address.");
        }

        String userPassword = DataValidation.getParam(params, ParamConfig.PARAM_USER_PASSWORD_KEY);
        if (DataValidation.isNull(userPassword)) {
            errors.add(ParamConfig.PARAM_USER_PASSWORD_KEY + " is required.");
        }

        user.setUserEmail(userEmail);
        user.setUserPassword(CryptoUtil.toPasswordHash(userPassword));
    }

    @Override
    public Object get() {
        return user;
    }

    @Override
    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    @Override
    public ArrayList<String> getErrors() {
        return errors;
    }
}
