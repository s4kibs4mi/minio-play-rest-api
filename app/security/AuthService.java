package security;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

public class AuthService extends Security.Authenticator {

    @Override
    public String getUsername(Http.Context context) {
        return super.getUsername(context);
    }

    @Override
    public Result onUnauthorized(Http.Context context) {

        return super.onUnauthorized(context);
    }
}
