package controllers;

import configs.MinioConfig;
import play.Logger;
import play.mvc.Http;
import play.mvc.Result;
import services.MinioObjectService;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * := Coded with love by Sakib Sami on 7/25/17.
 * := s4kibs4mi@gmail.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

public class MinioObjectController extends MinioController {
    private String TAG = this.getClass().getSimpleName();

    private MinioObjectService minioObjectService = new MinioObjectService();

    public Result create(String bucketName) {
        try {
            Http.MultipartFormData<File> requestedData = request().body().asMultipartFormData();
            for (Http.MultipartFormData.FilePart<File> requestedFile : requestedData.getFiles()) {
                String objectName = MinioConfig.getRandomText() + "-" + requestedFile.getFilename();
                FileInputStream objectInputStream = new FileInputStream(requestedFile.getFile());
                String objectContentType = requestedFile.getContentType();

                return ok(minioObjectService.createObject(bucketName, objectName,
                        objectInputStream, objectContentType).toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.debug(TAG + " : " + ex.getMessage());
        }

        return ok(getUnknownErrorResult().toString());
    }

    public Result getObject(String bucketName, String objectName) {
        try {
            InputStream inputStream = minioObjectService.getObject(bucketName, objectName);
            if (inputStream != null) {
                return ok(inputStream);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.debug(TAG + " : " + ex.getMessage());
        }
        return notFound("Content " + objectName + " not found in " + bucketName + ".");
    }
}
