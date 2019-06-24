package com.william.ftd_core;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.ArrayMap;
import android.util.Log;

import com.google.gson.Gson;
import com.william.ftd_core.constant.ServiceApi;
import com.william.ftd_core.entity.Result;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.param.LoginParam;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.william.ftd_core.constant.ServiceApi.JSON_MEDIA;

/**
 * todo 添加build模式
 */
public class FtdClient {

    private static final String TAG = "FtdClient";

    private Retrofit retrofit;
    private FtdService service;

    private Gson gson = new Gson();

    private String companyCode;
    private String appId;

    private User user;

    public FtdClient(String companyCode, String appId) {
        this.companyCode = companyCode;
        this.appId = appId;

        initRetrofit();
    }


    private void initRetrofit() {
//        val builder = OkHttpClient.Builder()
////                .addInterceptor(HeaderInterceptor())//设置Header
////                .addNetworkInterceptor(CacheInterceptor())//设置缓存
////                .addInterceptor(CacheInterceptor())
////                .cache(cache)
////                .connectTimeout(BuildConfig.DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
////                .readTimeout(BuildConfig.DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
////                .writeTimeout(BuildConfig.DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)//设置写的超时时间
////                .retryOnConnectionFailure(true)//错误重连

//        builder.addInterceptor { chain ->
//                val originReq = chain.request()
//            val req = originReq.newBuilder()
//                    .header(TENANT_CODE,BuildConfig.TENANT_CODE)
//                    .method(originReq.method(), originReq.body())
//                    .build()
//            chain.proceed(req)
//        }
////        调试模式打印Log日志
//        if (BuildConfig.DEBUG) {
//            builder.addInterceptor { chain ->
//                    val originReq = chain.request()
//                //---------请求之前------------
//                Logger.t(tag).d("正在请求：${originReq.url()}\n请求头：${originReq.headers()}\n请求体：${originReq.body()}")
//                //---------请求之后------------
//                val response = chain.proceed(originReq)
//                Logger.t(tag).d("获得响应：${response.body()}")
//                response
//            }
//        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {//debug模式添加log
            builder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    okhttp3.Request originReq = chain.request();
                    Log.d(TAG, "正在请求：" + originReq.url() + "\n请求头：" + "originReq.headers()" + "\n请求体：" + originReq.body());
                    okhttp3.Response response = chain.proceed(originReq);
                    Log.d(TAG, "获得响应：" + response.body());
                    return response;
                }
            });
        }
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        this.service = this.retrofit.create(FtdService.class);
    }

    /**
     * 登录面舌诊
     */
    @SuppressLint("CheckResult")
    public void login(final String phone) {
        service.getToken()
                .subscribeOn(Schedulers.io())
                .map(new Function<FtdResponse<String>, String>() {
                    @Override
                    public String apply(FtdResponse<String> stringFtdResponse) throws Exception {
                        if (stringFtdResponse == null || stringFtdResponse.getCode() != 1000) {
                            throw new Exception("");
                        }
                        return stringFtdResponse.getData();
                    }
                })
                .flatMap(new Function<String, SingleSource<FtdResponse<User>>>() {
                    @Override
                    public SingleSource<FtdResponse<User>> apply(String token) throws Exception {
                        LoginParam param = new LoginParam(phone, companyCode, appId, token);
                        String json = gson.toJson(param);
                        RequestBody requestBody = RequestBody.create(MediaType.parse(JSON_MEDIA), json);
                        return service.login(requestBody);
                    }
                })
                .map(new Function<FtdResponse<User>, User>() {
                    @Override
                    public User apply(FtdResponse<User> userFtdResponse) throws Exception {
                        if (userFtdResponse == null || userFtdResponse.getCode() != 1000) {
                            throw new Exception("");
                        }
                        return userFtdResponse.getData();
                    }
                })
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        FtdClient.this.user = user;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //todo 报错
                    }
                });

    }

//    @SuppressLint("CheckResult")
//    public void picUpload(File file,@ServiceApi.Type int type) {
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        Bitmap bitmap = BitmapFactory.decodeFile(file.getPath(),options);
//        int photoWidth = options.outWidth;
//        int photoHeight = options.outHeight;
//        bitmap.recycle();
//
//        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//        builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
//        builder.addFormDataPart("username", user.getMoble());
//        builder.addFormDataPart("userId", user.getPhrId());
//        builder.addFormDataPart("faceTongueFlag", String.valueOf(type));
//        builder.addFormDataPart("check_id", UUIDGenarator.getUUID());
//        builder.addFormDataPart("photoWidth", String.valueOf(photoWidth));
//        builder.addFormDataPart("photoHeight", String.valueOf(photoHeight));
//        builder.addFormDataPart("scheme_flag", UUIDGenarator.getUUID());
//        builder.addFormDataPart("equCode", ServiceApi.MAC);
//        builder.addFormDataPart("mApi", "7");
//        String rectX = "0";
//        String rectY= "0";
//        String rectWidth= "0";
//        String rectHeight= "0";
//        String takePhotoWidth= "0";
//        String takePhotoHeight= "0";
//        if (type != ServiceApi.FACE) {
//            rectX = "764";
//            rectY = "460";
//            rectWidth = "400";
//            rectHeight = "420";
//            takePhotoWidth = "1920";
//            takePhotoHeight = "1032";
//        }
//        builder.addFormDataPart("rectX", rectX);
//        builder.addFormDataPart("rectY", rectY);
//        builder.addFormDataPart("rectWidth", rectWidth);
//        builder.addFormDataPart("rectHeight", rectHeight);
//        builder.addFormDataPart("takePhotoWidth", takePhotoWidth);
//        builder.addFormDataPart("takePhotoHeight", takePhotoHeight);
//        service.picUpload(builder.build())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Result>() {
//                    @Override
//                    public void accept(Result result) throws Exception {
//                        int i = 0;
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        int i = 0;
//                    }
//                });
//    }

    @SuppressLint("CheckResult")
    public void picUpload(File file, @ServiceApi.Type int type) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(file.getPath(), options);
        int photoWidth = options.outWidth;
        int photoHeight = options.outHeight;
        bitmap.recycle();

        String rectX = "0";
        String rectY = "0";
        String rectWidth = "0";
        String rectHeight = "0";
        String takePhotoWidth = "0";
        String takePhotoHeight = "0";
        if (type != ServiceApi.FACE) {
            rectX = "764";
            rectY = "460";
            rectWidth = "400";
            rectHeight = "420";
            takePhotoWidth = "1920";
            takePhotoHeight = "1032";
        }
        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), fileBody);
        ArrayMap<String,String> params = new ArrayMap();
        params.put("username",user.getMoble());
        params.put("userId",user.getPhrId());
        params.put("faceTongueFlag",String.valueOf(type));
        params.put("check_id",UUIDGenarator.getUUID());
        params.put("photoWidth",String.valueOf(photoWidth));
        params.put("photoHeight",String.valueOf(photoHeight));
        params.put("scheme_flag",UUIDGenarator.getUUID());
        params.put("equCode",ServiceApi.MAC);
        params.put("mApi","7");
        params.put("rectX",rectX);
        params.put("rectY",rectY);
        params.put("rectWidth",rectWidth);
        params.put("rectHeight",rectHeight);
        params.put("takePhotoWidth",takePhotoWidth);
        params.put("takePhotoHeight",takePhotoHeight);
        ArrayMap<String,RequestBody> paramMap = convertToRequestBody(params,fileBody);
        service.picUpload(paramMap,filePart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        int i = 0;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        int i = 0;
                    }
                });
    }

    private ArrayMap<String,RequestBody> convertToRequestBody(ArrayMap<String,String> params,RequestBody fileBody){
        ArrayMap<String,RequestBody> paramMap = new ArrayMap<>();
        Set<Map.Entry<String,String>> entrySet = params.entrySet();
        for (Map.Entry<String,String> entry : entrySet){
            paramMap.put(entry.getKey(),RequestBody.create(MediaType.parse("text/plain"), entry.getValue()));
        }
        paramMap.put("file",fileBody);
        return paramMap;
    }

    public static FtdClient getInstance(String companyCode, String appId) {
        return Singleton.getInstance(companyCode, appId);
    }

    private static class Singleton {
        private static FtdClient client;

        private static FtdClient getInstance(String companyCode, String appId) {
            if (client == null) {
                client = new FtdClient(companyCode, appId);
            }
            return client;
        }
    }
}
