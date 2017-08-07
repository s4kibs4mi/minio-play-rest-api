package controllers;

import com.eclipsesource.json.JsonObject;
import com.fasterxml.jackson.databind.JsonNode;
import configs.ParamConfig;
import daos.UserDao;
import models.User;
import play.libs.Json;
import play.mvc.Result;
import security.DataTransformation;
import utils.AppUtil;
import validators.UserValidator;

/**
 * := Coded with love by Sakib Sami on 8/6/17.
 * := minio_play_rest_api
 * := root@sakib.ninja
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

public class UserController extends MinioController {
    private UserDao userDao = UserDao.getInstance();

    public Result create() {
        try {
            JsonObject result = new JsonObject();
            JsonNode params = request().body().asJson();
            UserValidator userValidator = new UserValidator(params);
            userValidator.validate();
            if (userValidator.hasErrors()) {
                result.add("errors", DataTransformation.toJsonArray(userValidator.getErrors()));
                result.add("code", ParamConfig.RESULT_ERROR);
            } else {
                User user = (User) userValidator.get();
                if (!userDao.isEmailExists(user.getUserEmail())) {
                    user.save();
                    result.add("code", ParamConfig.RESULT_SUCCESS);
                    result.add("message", "User has been created.");
                    result.add("data", user.toJson());
                } else {
                    user.save();
                    result.add("code", ParamConfig.RESULT_FAILED);
                    result.add("message", ParamConfig.PARAM_USER_EMAIL_KEY + " already exists.");
                }
            }
            return ok(Json.parse(result.toString()));
        } catch (Exception ex) {
            AppUtil.logError(this, ex.getMessage());
        }
        return ok(Json.parse(getUnknownErrorResult().toString()));
    }
}
