package services;

import configs.MinioConfig;
import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import play.Logger;

/**
 * := Coded with love by Sakib Sami on 7/25/17.
 * := s4kibs4mi@gmail.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

public class MinioConnectionService {

    private String TAG = this.getClass().getSimpleName();

    private static MinioConnectionService minioConnectionInstance;

    public static MinioConnectionService getInstance() {
        if (minioConnectionInstance == null) {
            minioConnectionInstance = new MinioConnectionService();
        }
        return minioConnectionInstance;
    }

    private MinioClient minioClient;

    private MinioConnectionService() {
        init();
    }

    private void init() {
        try {
            minioClient = new MinioClient(MinioConfig.getMsUri(),
                    MinioConfig.getMsAccessKey(), MinioConfig.getMsSecret());
        } catch (InvalidEndpointException | InvalidPortException e) {
            e.printStackTrace();
            Logger.debug(TAG + " : " + e.getMessage());
        }
    }

    public MinioClient getMinioClient() {
        return minioClient;
    }
}
