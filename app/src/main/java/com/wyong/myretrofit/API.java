package com.wyong.myretrofit;

import com.wyong.myretrofit.domain.CommentItem;
import com.wyong.myretrofit.domain.GetWithParamsResult;
import com.wyong.myretrofit.domain.JsonResult;
import com.wyong.myretrofit.domain.LoginResult;
import com.wyong.myretrofit.domain.PostFileResult;
import com.wyong.myretrofit.domain.PostWithParamsResult;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
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

    @POST("/post/comment")
    Call<PostWithParamsResult> postWithBody(@Body CommentItem commentItem);

    // 上传文件
    @Multipart
    @POST("/file/upload")
    Call<PostFileResult> postFile(@Part MultipartBody.Part part, @Header("token") String token);

    @Headers({"token:sdasdwe1234-1232312-43545-4325asd", "client:android", "version:1.0"})
    @Multipart
    @POST("/files/upload")
    Call<PostFileResult> postFiles(@Part List<MultipartBody.Part> parts);

    @Multipart
    @POST("/file/params/upload")
    Call<PostFileResult> postFileWithParams(@Part MultipartBody.Part part, @PartMap Map<String, String> params,
                                            @HeaderMap Map<String, String> headers);

    // 模拟登录 提交表单
    @FormUrlEncoded
    @POST("/login")
    Call<LoginResult> doLogin(@Field("userName") String userName, @Field("password") String password);

    @FormUrlEncoded
    @POST("/login")
    Call<LoginResult> doLoginWithMap(@FieldMap Map<String, String> params);

    // 文件下载
    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url String url);
}
