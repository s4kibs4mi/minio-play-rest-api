package security;

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

    @Override
    public String getUsername(Http.Context ctx) {

        return super.getUsername(ctx);
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {

        return super.onUnauthorized(ctx);
    }
}
