package com.wyong.myretrofit;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wyong.myretrofit.adapter.JsonResultListAdapter;
import com.wyong.myretrofit.domain.JsonResult;
import com.wyong.myretrofit.utils.RetrofitManager;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Retrofit 请求网络。
 *
 * <p>
 * * 服务器 接口地址：https://github.com/TrillGates/SOBAndroidMiniWeb
 * * <p>
 * * 真机调试地址：http://192.168.137.1:9102/get/param
 * * 模拟器调试地址：http://10.0.2.2:9102/get/text
 */
public class MainActivity extends AppCompatActivity {

    //    private static final String BASE_URL = "http://192.168.137.1:9102";//192.168.1.23:9102
    public static final String BASE_URL = "http://10.0.2.2:9102";
    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private JsonResultListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.result_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = 5;
                outRect.bottom = 5;
            }
        });
        mAdapter = new JsonResultListAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Retrofit 发起网络请求
     *
     * @param view
     */
    public void getRequest(View view) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

        API api = RetrofitManager.getRetrofit().create(API.class);
        Call<JsonResult> task = api.getJson();
        task.enqueue(new Callback<JsonResult>() {
            @Override
            public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                Log.d(TAG, "onResponse === > " + response.code());
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    JsonResult result = response.body();
                    updateList(result);
                }
            }

            @Override
            public void onFailure(Call<JsonResult> call, Throwable t) {
                Log.d(TAG, "onFailure === > " + t.toString());
            }
        });

//        Call<ResponseBody> task = api.getJson();
//        task.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Log.d(TAG, "onResponse === > " + response.code());
//                if (response.code() == HttpURLConnection.HTTP_OK) {
//                    try {
//                        Log.d(TAG, "json ===>" + response.body().string());
//                        String result = response.body().string();
//
//                        Gson gson = new Gson();
//                        JsonResult jsonResult = gson.fromJson(result, JsonResult.class);
//                        updateList(jsonResult);
//
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d(TAG, "onFailure === > " + t.toString());
//            }
//        });
    }

    private void updateList(JsonResult jsonResult) {
        mAdapter.setData(jsonResult);
    }
}