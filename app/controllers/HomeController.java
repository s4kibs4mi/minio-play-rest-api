package controllers;

import play.mvc.Result;

/**
 * := Coded with love by Sakib Sami on 8/6/17.
 * := minio_play_rest_api
 * := root@sakib.ninja
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

public class HomeController extends MinioController {

    public Result home() {
        return ok();
    }

    public Result outofthebox() {
        return ok();
    }
}
