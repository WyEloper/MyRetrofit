package com.wyong.myretrofit.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 请求网络。
 *
 * <p>
 * * 服务器 接口地址：https://github.com/TrillGates/SOBAndroidMiniWeb
 * * <p>
 * * 真机调试地址：http://192.168.1.23:9102/get/param
 * * 模拟器调试地址：http://10.0.2.2:9102/get/text
 */
public class RetrofitManager {

//        private static final String BASE_URL = "http://192.168.1.23:9102";//192.168.1.23:9102
    private static final String BASE_URL = "http://10.0.2.2:9102";
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Retrofit getRetrofit(){
        return retrofit;
    }

}

