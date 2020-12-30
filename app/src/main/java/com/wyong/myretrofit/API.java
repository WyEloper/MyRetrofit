package com.wyong.myretrofit;

import com.wyong.myretrofit.domain.GetWithParamsResult;
import com.wyong.myretrofit.domain.JsonResult;
import com.wyong.myretrofit.domain.PostWithParamsResult;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface API {
    @GET("/get/text")
    Call<JsonResult> getJson();
//    Call<ResponseBody> getJson();

    @GET("/get/param")
    Call<GetWithParamsResult> getWithParams(@Query("keyword") String keyword,
                                            @Query("page") int page,
                                            @Query("order") String order);

    @GET("/get/param")
    Call<GetWithParamsResult> getWithParams(@QueryMap Map<String, Object> params);

    @POST("/post/string")
    Call<PostWithParamsResult> postWithParams(@Query("string") String content);

    @POST
    Call<PostWithParamsResult> postWithUrl(@Url String url);


}
