package security;

import com.fasterxml.jackson.databind.JsonNode;
import configs.ParamConfig;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

public class AuthService extends Security.Authenticator {

    @Override
    public String getUsername(Http.Context context) {
        JsonNode params = context.request().body().asJson();
        if (params.get(ParamConfig.PARAM_ACCESS_TOKEN_KEY) != null) {
            String accessToken = params.get(ParamConfig.PARAM_ACCESS_TOKEN_KEY).asText();
            accessToken = DataValidation.filterForSqlInjection(accessToken);
            if (params.get(ParamConfig.PARAM_USER_HASH_KEY) != null) {
                String userHash = params.get(ParamConfig.PARAM_USER_HASH_KEY).asText();
            }
        }
        return super.getUsername(context);
    }

    @Override
    public Result onUnauthorized(Http.Context context) {

        return super.onUnauthorized(context);
    }
}
