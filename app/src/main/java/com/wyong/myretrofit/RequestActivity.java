package com.wyong.myretrofit;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.wyong.myretrofit.domain.CommentItem;
import com.wyong.myretrofit.domain.GetWithParamsResult;
import com.wyong.myretrofit.domain.LoginResult;
import com.wyong.myretrofit.domain.PostFileResult;
import com.wyong.myretrofit.domain.PostWithParamsResult;
import com.wyong.myretrofit.utils.RetrofitManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestActivity extends AppCompatActivity {

    private static final String TAG = "RequestActivity";
    private static final int REQUEST_CODE = 1;
    private API mApi;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        mApi = RetrofitManager.getRetrofit().create(API.class);

        // 动态获取权限（简便快速的写法）-- https://www.sunofbeach.net/a/1192351879502237696
        int readPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        int writePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (readPermission != PackageManager.PERMISSION_GRANTED || writePermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            //TODO:处理权限请求结果
            //判断结果
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "has permissions..");
                //有权限
            } else {
                Log.d(TAG, "no permissionS...");
                //没权限
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        && !ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    //走到这里，说明用户之前用户禁止权限的同时，勾选了不再询问
                    //那么，你需要弹出一个dialog，提示用户需要权限，然后跳转到设置里头去打开。
                    Log.d(TAG, "用户之前勾选了不再询问...");
                    //TODO:弹出一个框框，然后提示用户说需要开启权限。
                    //TODO:用户点击确定的时候，跳转到设置里去
                    //Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    //Uri uri = Uri.fromParts("package", getPackageName(), null);
                    //intent.setData(uri);
                    ////在activity结果范围的地方，再次检查是否有权限
                    //startActivityForResult(intent, PERMISSION_REQUEST_CODE);
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
                    //请求权限
                    Log.d(TAG, "请求权限...");
                }
            }
        }
    }

    public void getWithParams(View view) {
        Call<GetWithParamsResult> task = mApi.getWithParams("我是搜索关键字", 10, "0");
        task.enqueue(new Callback<GetWithParamsResult>() {
            @Override
            public void onResponse(Call<GetWithParamsResult> call, Response<GetWithParamsResult> response) {
                int code = response.code();
                Log.d(TAG, "code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "onResponse === > " + response.body());
                    showToast(RequestActivity.this, "带参数的get请求成功：" + response.body());
                }
            }

            @Override
            public void onFailure(Call<GetWithParamsResult> call, Throwable t) {
                Log.d(TAG, "onFailure === > " + t.toString());
            }
        });
    }

    /**
     * 带参数（map集合）的get请求
     *
     * @param view
     */
    public void getWithParamsMap(View view) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", "我是搜索关键字");
        params.put("page", 10);
        params.put("order", "0");

        Call<GetWithParamsResult> task = mApi.getWithParams(params);
        task.enqueue(new Callback<GetWithParamsResult>() {
            @Override
            public void onResponse(Call<GetWithParamsResult> call, Response<GetWithParamsResult> response) {
                int code = response.code();
                Log.d(TAG, "code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "onResponse === > " + response.body());
                    showToast(RequestActivity.this, "带map参数的get请求成功：" + response.body());
                }
            }

            @Override
            public void onFailure(Call<GetWithParamsResult> call, Throwable t) {
                Log.d(TAG, "onFailure === > " + t.toString());
            }
        });
    }

    public void postWithParams(View view) {
        Call<PostWithParamsResult> task = mApi.postWithParams("这是我的内容。。。");
        task.enqueue(new Callback<PostWithParamsResult>() {
            @Override
            public void onResponse(Call<PostWithParamsResult> call, Response<PostWithParamsResult> response) {
                int code = response.code();
                Log.d(TAG, "code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "onResponse === > " + response.body());
                    showToast(RequestActivity.this, "带参数的post请求成功：" + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostWithParamsResult> call, Throwable t) {
                Log.d(TAG, "onFailure === > " + t.toString());
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
                Log.d(TAG, "code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "onResponse === > " + response.body());
                    showToast(RequestActivity.this, "带url参数的post请求成功：" + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostWithParamsResult> call, Throwable t) {
                Log.d(TAG, "onFailure === > " + t.toString());
            }
        });

    }

    /**
     * Post请求添加body参数 @Body 注解
     *
     * @param view
     */
    public void postWithBody(View view) {
        CommentItem commentItem = new CommentItem("23131", "你的文章写的也太好了。。。");
        Call<PostWithParamsResult> task = mApi.postWithBody(commentItem);
        task.enqueue(new Callback<PostWithParamsResult>() {
            @Override
            public void onResponse(Call<PostWithParamsResult> call, Response<PostWithParamsResult> response) {
                int code = response.code();
                Log.d(TAG, "code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "onResponse === > " + response.body());
                    showToast(RequestActivity.this, "Body参数的post请求成功：" + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostWithParamsResult> call, Throwable t) {
                Log.d(TAG, "onFailure === > " + t.toString());
            }
        });
    }

    private static Toast sToast;

    @SuppressLint("ShowToast")
    private static void showToast(Context context, String content) {
        if (sToast == null) {
            sToast = Toast.makeText(context.getApplicationContext(), content, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(content);
        }
        sToast.show();
    }

    /**
     * 单文件上传 @Part注解
     *
     * @param view
     */
    public void postFile(View view) {
        MultipartBody.Part part = createPartByPathAndKey("/storage/emulated/0/Download/u=3690816660,1217381139&fm=193&f=GIF.jpeg", "file");
        Call<PostFileResult> task = mApi.postFile(part, "sdasdwe1234-1232312-43545-4325asd");
        task.enqueue(new Callback<PostFileResult>() {
            @Override
            public void onResponse(Call<PostFileResult> call, Response<PostFileResult> response) {
                int code = response.code();
                Log.d(TAG, "code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "onResponse === > " + response.body());
                    showToast(RequestActivity.this, "post单文件上传成功：" + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostFileResult> call, Throwable t) {
                Log.d(TAG, "onFailure === > " + t.toString());
            }
        });
    }

    private MultipartBody.Part createPartByPathAndKey(String path, String key) {
        File file = new File(path);
        RequestBody body = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData(key, file.getName(), body);
        return part;
    }

    /**
     * 多文件上传 @Part注解
     *
     * @param view
     */
    public void postFiles(View view) {
        List<MultipartBody.Part> parts = new ArrayList<>();
        MultipartBody.Part partOne = createPartByPathAndKey("/storage/emulated/0/Download/u=4081380758,4110453945&fm=193&f=GIF.jpeg", "files");
        parts.add(partOne);
        MultipartBody.Part partTwo = createPartByPathAndKey("/storage/emulated/0/Download/u=359481445,1584959105&fm=193&f=GIF.jpeg", "files");
        parts.add(partTwo);
        MultipartBody.Part PartThree = createPartByPathAndKey("/storage/emulated/0/Download/u=3136391786,358304618&fm=193&f=GIF.jpeg", "files");
        parts.add(PartThree);
        MultipartBody.Part partFour = createPartByPathAndKey("/storage/emulated/0/Download/u=188158670,1487427899&fm=193&f=GIF.jpeg", "files");
        parts.add(partFour);
        MultipartBody.Part partFive = createPartByPathAndKey("/storage/emulated/0/Download/u=1558840578,3051104172&fm=193&f=GIF.jpeg", "files");
        parts.add(partFive);
        Call<PostFileResult> task = mApi.postFiles(parts);
        task.enqueue(new Callback<PostFileResult>() {
            @Override
            public void onResponse(Call<PostFileResult> call, Response<PostFileResult> response) {
                int code = response.code();
                Log.d(TAG, "code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "onResponse === > " + response.body());
                    showToast(RequestActivity.this, "post多文件上传成功：" + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostFileResult> call, Throwable t) {
                Log.d(TAG, "onFailure === > " + t.toString());
            }
        });
    }

    /**
     * 上传文件携带参数 @Part注解
     *
     * @param view
     */
    public void postFilesWithParams(View view) {
        MultipartBody.Part part = createPartByPathAndKey("/storage/emulated/0/Download/u=3690816660,1217381139&fm=193&f=GIF.jpeg", "file");
        Map<String, String> params = new HashMap<>();
        params.put("description", "这是一张新年海报，新年快乐！");
        params.put("isFree", "true");
        Map<String, String> headers = new HashMap<>();
        headers.put("token", "sdasdwe1234-1232312-43545-4325asd");
        headers.put("client", "iPhone12");
        headers.put("version", "1.1");
        Call<PostFileResult> task = mApi.postFileWithParams(part, params, headers);
        task.enqueue(new Callback<PostFileResult>() {
            @Override
            public void onResponse(Call<PostFileResult> call, Response<PostFileResult> response) {
                int code = response.code();
                Log.d(TAG, "code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "onResponse === > " + response.body());
                    showToast(RequestActivity.this, "文件上传携带参数成功：" + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostFileResult> call, Throwable t) {
                Log.d(TAG, "onFailure === > " + t.toString());
            }
        });
    }

    /**
     * doLogin 模拟登录提交表单
     *
     * @param view 泛型 -- 保证数据的安全性，对数据进行约束
     */
    public void doLogin(View view) {
        Call<LoginResult> task = mApi.doLogin("wyong", "123456");
        task.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                int code = response.code();
                Log.d(TAG, "code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "onResponse === > " + response.body());
                    showToast(RequestActivity.this, "模拟登录提交表单成功：" + response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.d(TAG, "onFailure === > " + t.toString());
            }
        });
    }

    /**
     * 用map集合提交表单 @FieldMap
     *
     * @param view
     */
    public void doLoginWithMap(View view) {
        Map<String, String> params = new HashMap<>();
        params.put("userName", "Eloper");
        params.put("password", "123456");
        Call<LoginResult> task = mApi.doLoginWithMap(params);
        task.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                int code = response.code();
                Log.d(TAG, "code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "onResponse === > " + response.body());
                    showToast(RequestActivity.this, "map模拟登录提交表单成功：" + response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.d(TAG, "onFailure === > " + t.toString());
            }
        });
    }

    /**
     * 文件下载 @Streaming 注解
     *
     * @param view
     */
    public void downloadFile(View view) {
        String url = "/download/8";
        Call<ResponseBody> task = mApi.downloadFile(url);
        task.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                Log.d(TAG, "code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "current thread name is ===> " + Thread.currentThread().getName());
                    //知道文件名称--header里面
                    Headers headers = response.headers();
                    String fileNameHeader = headers.get("Content-disposition");
                    String fileName = "未命名.png";
                    if (fileNameHeader != null) {
                        fileName = fileNameHeader.replace("attachment; filename=", "");
                        Log.d(TAG, "fileName ===> " + fileName);
                    }
//                    for (int i = 0; i < headers.size(); i++) {
//                        String key = headers.name(i);
//                        String value = headers.value(i);
//                        Log.d(TAG,"key ===> " + key);
//                        Log.d(TAG,"value ===> " + value);
//                    }
                    //写文件，但是这里是UI线程，不可以写
                    writeString2Disk(response, fileName);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure === > " + t.toString());
            }
        });
    }

    /**
     * 下载的文件保存到硬盘
     *
     * @param response
     * @param fileName
     */
    private void writeString2Disk(Response<ResponseBody> response, String fileName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = response.body().byteStream();
                //拿到保存图片的路径
//                File exPath = Environment.getExternalStorageDirectory();
//                Log.d(TAG,"exPath ===> " + exPath);
                File baseOutFile = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                Log.d(TAG, "baseOutFile ===> " + baseOutFile);//baseOutFile ===> /storage/emulated/0/Android/data/com.wyong.myretrofit/files/Pictures
                File outFile = new File(baseOutFile, fileName);
                Log.d(TAG, "outFile ===> " + outFile);
                //开始往硬盘上写入
                try {
                    FileOutputStream fos = new FileOutputStream(outFile);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inputStream.read(buffer)) != -1) {
                        fos.write(buffer, 0, length);
                        Looper.prepare();
                        showToast(RequestActivity.this, "下载文件成功，保存路径为：" + outFile);
                        Looper.loop();
                    }
                    //关闭流
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}