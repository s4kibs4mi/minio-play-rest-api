package services;

import com.eclipsesource.json.JsonObject;
import configs.MinioConfig;
import io.minio.errors.*;
import org.xmlpull.v1.XmlPullParserException;
import play.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * := Coded with love by Sakib Sami on 7/25/17.
 * := s4kibs4mi@gmail.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

public class MinioObjectService {
    private String TAG = this.getClass().getSimpleName();

    private MinioConnectionService minioConnectionService;

    public MinioObjectService() {
        minioConnectionService = MinioConnectionService.getInstance();
    }

    public JsonObject createObject(String bucketName, String objectName, InputStream inputStream, String contentType) {
        JsonObject result = new JsonObject();
        try {
            minioConnectionService.getMinioClient().putObject(bucketName, objectName, inputStream, contentType);
            JsonObject data = new JsonObject();
            data.add("object_name", objectName);
            data.add("object_url", MinioConfig.getMsHost() +
                    File.separator + "bucket" + File.separator + bucketName +
                    File.separator + "object" + File.separator + objectName);

            result.add("code", 200);
            result.add("response", "success");
            result.add("message", "Object " + objectName + " has been created.");
            result.add("data", data);
        } catch (InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException |
                InvalidKeyException | NoResponseException | XmlPullParserException | ErrorResponseException |
                InternalException | InvalidArgumentException e) {
            e.printStackTrace();
            Logger.debug(TAG + " : " + e.getMessage());

            result.add("code", 201);
            result.add("response", "failed");
            result.add("message", e.getMessage());
        }
        return result;
    }

    public InputStream getObject(String bucketName, String objectName) {
        try {
            return minioConnectionService.getMinioClient().getObject(bucketName, objectName);
        } catch (InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | InvalidKeyException | IOException | XmlPullParserException | NoResponseException | InternalException | ErrorResponseException | InvalidArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
