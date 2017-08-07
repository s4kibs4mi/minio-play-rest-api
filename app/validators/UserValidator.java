package validators;

import com.fasterxml.jackson.databind.JsonNode;
import configs.ParamConfig;
import models.User;
import security.DataValidation;
import utils.AppUtil;
import utils.CryptoUtil;

import java.util.ArrayList;

/**
 * := Coded with love by Sakib Sami on 8/6/17.
 * := minio_play_rest_api
 * := root@sakib.ninja
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

public class UserValidator implements IValidator {
    private JsonNode params;
    private ArrayList<String> errors;
    private User user;

    public UserValidator(JsonNode params) {
        this.params = params;
        this.errors = new ArrayList<>();
        this.user = new User();
    }

    @Override
    public void validate() {
        user.setUserName(DataValidation.getParam(params, ParamConfig.PARAM_USER_NAME_KEY));
        if (DataValidation.isNull(user.getUserName())) {
            errors.add(ParamConfig.PARAM_USER_NAME_KEY + " is required.");
        }

        user.setUserEmail(DataValidation.getParam(params, ParamConfig.PARAM_USER_EMAIL_KEY));
        if (DataValidation.isNull(user.getUserEmail())) {
            errors.add(ParamConfig.PARAM_USER_EMAIL_KEY + " is required.");
        } else if (!DataValidation.isEmail(user.getUserEmail())) {
            errors.add("Invalid email address.");
        }

        user.setUserPassword(DataValidation.getParam(params, ParamConfig.PARAM_USER_PASSWORD_KEY));
        if (DataValidation.isNull(user.getUserPassword())) {
            errors.add(ParamConfig.PARAM_USER_PASSWORD_KEY + " is required.");
        } else {
            user.setUserPassword(CryptoUtil.toPasswordHash(user.getUserPassword()));
        }

        user.setUserHash(AppUtil.getUUID());
        user.setUserId(AppUtil.getUUID());
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
