package security;

import com.eclipsesource.json.JsonObject;
import configs.ParamConfig;
import daos.SessionDao;
import daos.UserDao;
import models.Session;
import models.User;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

/**
 * := Coded with love by Sakib Sami on 8/7/17.
 * := minio_play_rest_api
 * := root@sakib.ninja
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

public class Authorizer extends Security.Authenticator {
    private SessionDao sessionDao = SessionDao.getInstance();
    private UserDao userDao = UserDao.getInstance();

    @Override
    public String getUsername(Http.Context ctx) {
        Http.Request request = ctx.request();
        String userHash = request.getHeader(ParamConfig.PARAM_USER_HASH_KEY);
        String accessToken = request.getHeader(ParamConfig.PARAM_ACCESS_TOKEN_KEY);
        if (!DataValidation.isNull(userHash) && !DataValidation.isNull(accessToken)) {
            User user = userDao.find(userHash);
            if (!DataValidation.isNull(user)) {
                Session session = sessionDao.find(user.getUserId(), accessToken);
                if (session != null && !session.isExpired()) {
                    return session.getAccessToken();
                }
            }
        }
        return null;
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        JsonObject result = new JsonObject();
        result.add("code", ParamConfig.RESULT_UNAUTHORIZED);
        result.add("response", "Unauthorized request.");
        return ok(Json.parse(result.toString()));
    }
}
