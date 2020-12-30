package com.wyong.myretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wyong.myretrofit.domain.GetWithParamsResult;
import com.wyong.myretrofit.domain.PostWithParamsResult;
import com.wyong.myretrofit.utils.RetrofitManager;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestActivity extends AppCompatActivity {

    private static final String TAG = "RequestActivity";
    private API mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        mApi = RetrofitManager.getRetrofit().create(API.class);
    }

    public void getWithParams(View view) {
        Call<GetWithParamsResult> task = mApi.getWithParams("我是搜索关键字", 10, "0");
        task.enqueue(new Callback<GetWithParamsResult>() {
            @Override
            public void onResponse(Call<GetWithParamsResult> call, Response<GetWithParamsResult> response) {
                int code = response.code();
                Log.d(TAG,"code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG,"onResponse === > " + response.body());
                }
            }

            @Override
            public void onFailure(Call<GetWithParamsResult> call, Throwable t) {
                Log.d(TAG,"onFailure === > " + t.toString());
            }
        });
    }

    public void getWithParamsMap(View view) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword","我是搜索关键字");
        params.put("page",10);
        params.put("order","0");

        Call<GetWithParamsResult> task = mApi.getWithParams(params);
        task.enqueue(new Callback<GetWithParamsResult>() {
            @Override
            public void onResponse(Call<GetWithParamsResult> call, Response<GetWithParamsResult> response) {
                int code = response.code();
                Log.d(TAG,"code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG,"onResponse === > " + response.body());
                }
            }

            @Override
            public void onFailure(Call<GetWithParamsResult> call, Throwable t) {
                Log.d(TAG,"onFailure === > " + t.toString());
            }
        });
    }

    public void postWithParams(View view) {
        Call<PostWithParamsResult> task = mApi.postWithParams("这是我的内容。。。");
        task.enqueue(new Callback<PostWithParamsResult>() {
            @Override
            public void onResponse(Call<PostWithParamsResult> call, Response<PostWithParamsResult> response) {
                int code = response.code();
                Log.d(TAG,"code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG,"onResponse === > " + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostWithParamsResult> call, Throwable t) {
                Log.d(TAG,"onFailure === > " + t.toString());
            }
        });

    }

    public void postWithUrl(View view) {
        String url = "/post/string?string=内容测试内容";
        Call<PostWithParamsResult> task = mApi.postWithUrl(url);
        task.enqueue(new Callback<PostWithParamsResult>() {
            @Override
            public void onResponse(Call<PostWithParamsResult> call, Response<PostWithParamsResult> response) {
                int code = response.code();
                Log.d(TAG,"code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG,"onResponse === > " + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostWithParamsResult> call, Throwable t) {
                Log.d(TAG,"onFailure === > " + t.toString());
            }
        });

    }
}