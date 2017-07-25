package services;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import org.xmlpull.v1.XmlPullParserException;
import play.Logger;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * := Coded with love by Sakib Sami on 7/25/17.
 * := s4kibs4mi@gmail.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

public class MinioBucketService {
    private String TAG = this.getClass().getSimpleName();

    private MinioConnectionService connectionService;

    public MinioBucketService() {
        connectionService = MinioConnectionService.getInstance();
    }

    public JsonObject create(String bucketName) {
        JsonObject result = new JsonObject();
        try {
            if (!connectionService.getMinioClient().bucketExists(bucketName)) {
                connectionService.getMinioClient().makeBucket(bucketName);

                result.add("code", 200);
                result.add("response", "success");
                result.add("message", "Bucket " + bucketName + " has been created.");
            } else {
                result.add("code", 200);
                result.add("response", "success");
                result.add("message", "Bucket " + bucketName + " exists.");
            }
        } catch (InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException | NoResponseException | XmlPullParserException | ErrorResponseException | InternalException e) {
            e.printStackTrace();
            Logger.debug(TAG + " :" + e.getMessage());

            result = appendDefaultErrorMessage(result);
            result.add("message", e.getMessage());
        } catch (RegionConflictException e) {
            e.printStackTrace();

            result.add("code", 201);
            result.add("response", "failed");
            result.add("message", e.getMessage());
        }
        return result;
    }

    public JsonObject list() {
        JsonObject result = new JsonObject();
        try {
            JsonArray bucketAsJsonResult = new JsonArray();
            List<Bucket> bucketList = connectionService.getMinioClient().listBuckets();
            for (Bucket bucket : bucketList) {
                JsonObject bucketAsJson = new JsonObject();
                bucketAsJson.add("bucket_name", bucket.name());
                bucketAsJson.add("bucket_creation_date", bucket.creationDate().getTime());
                bucketAsJsonResult.add(bucketAsJson);
            }

            result.add("code", 200);
            result.add("response", "success");
            result.add("data", bucketAsJsonResult);
        } catch (InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException | NoResponseException | XmlPullParserException | ErrorResponseException | InternalException e) {
            e.printStackTrace();
            Logger.debug(TAG + " :" + e.getMessage());

            result = appendDefaultErrorMessage(result);
            result.add("message", e.getMessage());
        }
        return result;
    }

    public JsonObject remove(String bucketName) {
        JsonObject result = new JsonObject();
        try {
            connectionService.getMinioClient().removeBucket(bucketName);

            result.add("code", 200);
            result.add("response", "success");
            result.add("message", "Bucket " + bucketName + " has been removed.");
            result.add("data", true);
        } catch (InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException | NoResponseException | XmlPullParserException | ErrorResponseException | InternalException e) {
            e.printStackTrace();
            Logger.debug(TAG + " :" + e.getMessage());

            result = appendDefaultErrorMessage(result);
            result.add("message", e.getMessage());
            result.add("data", false);
        }
        return result;
    }

    public JsonObject check(String bucketName) {
        JsonObject result = new JsonObject();
        try {
            if (connectionService.getMinioClient().bucketExists(bucketName)) {
                result.add("message", "Bucket " + bucketName + " exists.");
                result.add("data", true);
            } else {
                result.add("message", "Bucket " + bucketName + " doesn't exists.");
                result.add("data", false);
            }

            result.add("code", 200);
            result.add("response", "success");
        } catch (InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException | NoResponseException | XmlPullParserException | ErrorResponseException | InternalException e) {
            e.printStackTrace();
            Logger.debug(TAG + " :" + e.getMessage());

            result = appendDefaultErrorMessage(result);
            result.add("message", e.getMessage());
        }
        return result;
    }

    public JsonObject appendDefaultErrorMessage(JsonObject result) {
        result.add("code", 201);
        result.add("response", "failed");
        return result;
    }
}
