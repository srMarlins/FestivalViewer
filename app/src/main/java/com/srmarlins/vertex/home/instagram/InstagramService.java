package com.srmarlins.vertex.home.instagram;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by JaredFowler on 7/22/2016.
 */
public interface InstagramService {

    String BASE_URL = "http://www.instagram.com";

    @GET("explore/tags/{tag}")
    Call<ResponseBody> getInstagramPublicTagHtml(@Path("tag") String tag);

    @GET
    Call<ResponseBody> getInstagramPublicImageLink(@Url String url);
}
