package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import configs.ParamConfig;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Security;
import security.Authorizer;
import services.MinioBucketService;

/**
 * := Coded with love by Sakib Sami on 7/25/17.
 * := s4kibs4mi@gmail.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

@Security.Authenticated(Authorizer.class)
public class MinioBucketController extends MinioController {
    private String TAG = this.getClass().getSimpleName();

    private MinioBucketService minioBucketService = new MinioBucketService();

    public Result create() {
        try {
            JsonNode params = request().body().asJson();
            if (params.get(ParamConfig.PARAM_BUCKET_KEY) != null) {
                String bucketName = params.get(ParamConfig.PARAM_BUCKET_KEY).asText();
                return ok(Json.parse(minioBucketService.create(bucketName).toString()));
            }
        } catch (Exception ex) {
            Logger.debug(TAG + " : " + ex.getMessage());
            ex.printStackTrace();
        }
        return ok(getUnknownErrorResult().toString());
    }

    public Result list() {
        return ok(minioBucketService.list().toString());
    }

    public Result remove(String bucketName) {
        try {
            return ok(Json.parse(minioBucketService.remove(bucketName).toString()));
        } catch (Exception ex) {
            Logger.debug(TAG + " : " + ex.getMessage());
            ex.printStackTrace();
        }
        return ok(getUnknownErrorResult().toString());
    }

    public Result check(String bucketName) {
        try {
            return ok(Json.parse(minioBucketService.check(bucketName).toString()));
        } catch (Exception ex) {
            Logger.debug(TAG + " : " + ex.getMessage());
            ex.printStackTrace();
        }
        return ok(getUnknownErrorResult().toString());
    }
}
