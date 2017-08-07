package controllers;

import com.eclipsesource.json.JsonObject;
import com.fasterxml.jackson.databind.JsonNode;
import configs.ParamConfig;
import daos.SessionDao;
import daos.UserDao;
import models.Session;
import models.User;
import play.libs.Json;
import play.mvc.Result;
import scala.App;
import security.DataTransformation;
import security.DataValidation;
import utils.AppUtil;
import utils.CryptoUtil;
import validators.AuthValidator;

/**
 * := Coded with love by Sakib Sami on 8/6/17.
 * := minio_play_rest_api
 * := root@sakib.ninja
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

public class AuthController extends MinioController {
    private UserDao userDao = UserDao.getInstance();
    private SessionDao sessionDao = SessionDao.getInstance();

    public Result login() {
        try {
            JsonObject result = new JsonObject();
            JsonNode params = request().body().asJson();
            AuthValidator authValidator = new AuthValidator(params);
            authValidator.validate();
            if (authValidator.hasErrors()) {
                result.add("errors", DataTransformation.toJsonArray(authValidator.getErrors()));
                result.add("code", ParamConfig.RESULT_ERROR);
            } else {
                User user = (User) authValidator.get();
                user = userDao.find(user.getUserEmail(), user.getUserPassword());
                if (DataValidation.isNull(user)) {
                    result.add("response", ParamConfig.PARAM_USER_EMAIL_KEY + " & "
                            + ParamConfig.PARAM_USER_PASSWORD_KEY + " doesn't match.");
                    result.add("code", ParamConfig.RESULT_FAILED);
                } else {
                    Session session = CryptoUtil.getNewSession(user);
                    session.save();
                    result.add("code", ParamConfig.RESULT_SUCCESS);
                    result.add("data", session.toJson());
                }
            }
            return ok(Json.parse(result.toString()));
        } catch (Exception ex) {
            ex.printStackTrace();
            AppUtil.logError(this, ex.getMessage());
        }
        return ok(Json.parse(getUnknownErrorResult().toString()));
    }

    public Result logout(String userHash, String accessToken) {
        try {
            JsonObject result = new JsonObject();
            User user = userDao.find(userHash);
            if (!DataValidation.isNull(user)) {
                Session session = sessionDao.find(user.getUserId(), accessToken);

                if (!DataValidation.isNull(session)) {
                    session.delete();
                    result.add("code", ParamConfig.RESULT_SUCCESS);
                    result.add("response", "Session has been deleted.");
                } else {
                    result.add("code", ParamConfig.RESULT_FAILED);
                    result.add("response", "Invalid " + ParamConfig.PARAM_ACCESS_TOKEN_KEY + ".");
                }
            } else {
                result.add("code", ParamConfig.RESULT_FAILED);
                result.add("response", "Invalid " + ParamConfig.PARAM_USER_HASH_KEY + ".");
            }
            return ok(Json.parse(result.toString()));
        } catch (Exception ex) {
            AppUtil.logError(this, ex.getMessage());
        }
        return ok(Json.parse(getUnknownErrorResult().toString()));
    }
}
