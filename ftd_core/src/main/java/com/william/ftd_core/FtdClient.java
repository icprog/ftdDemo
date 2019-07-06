package com.william.ftd_core;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.william.ftd_core.callback.BaseCallback;
import com.william.ftd_core.callback.FtdGetAnaylzerCallback;
import com.william.ftd_core.callback.FtdLastReportCallback;
import com.william.ftd_core.callback.FtdLoginCallback;
import com.william.ftd_core.callback.FtdMicroTipCallback;
import com.william.ftd_core.callback.FtdPicUploadCallback;
import com.william.ftd_core.callback.FtdQuestionListCallback;
import com.william.ftd_core.callback.FtdSubmitCallback;
import com.william.ftd_core.constant.ServiceApi;
import com.william.ftd_core.entity.AnalyzeResultBean;
import com.william.ftd_core.entity.AskBean;
import com.william.ftd_core.entity.Conclusion;
import com.william.ftd_core.entity.FtdResponse;
import com.william.ftd_core.entity.MicroTipBean;
import com.william.ftd_core.entity.QuestionBean;
import com.william.ftd_core.entity.ReportBean;
import com.william.ftd_core.entity.Result;
import com.william.ftd_core.entity.UploadResult;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;
import com.william.ftd_core.param.GetAnalyzerParam;
import com.william.ftd_core.param.GetQuestionParam;
import com.william.ftd_core.param.GetReportParam;
import com.william.ftd_core.param.InitParam;
import com.william.ftd_core.param.LoginParam;
import com.william.ftd_core.param.SubmitAnswerParam;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FtdClient {

    private static final String TAG = "FtdClient";

    private Retrofit retrofit;
    private FtdService service;

    private Gson gson = new Gson();

    private String appKey;
    private String appId;
    private String appCode;
    private String companyCode;
    private String appSecret;
    private String phrAppKey;
    private String phrAppSecret;

    private User user;

    //    private String schemeId = "2581181533264a9389efb46fc5d2da16";
    private String schemeId;

    public static FtdClient getInstance() {
        return SingletonInstance.INSTANCE;
    }

    private static class SingletonInstance {
        private static FtdClient INSTANCE = new FtdClient();
    }

    public void init(final InitParam param) {

        this.appId = param.getAppId();
        this.appKey = param.getAppKey();
        this.appSecret = param.getAppSecret();
        this.companyCode = param.getCompanyCode();

        this.phrAppKey = param.getPhrAppKey();
        this.phrAppSecret = param.getPhrAppSecret();

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES);

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originReq = chain.request();
                Request req = originReq.newBuilder()
                        .header(ServiceApi.APP_ID, appId)
                        .header(ServiceApi.APP_CODE, param.getAppCode())
                        .header(ServiceApi.PLACE_COMPANY_ID, param.getCompanyId())
                        .header(ServiceApi.PLACE_COMPANY_P_ID, param.getCompanyPid())
                        .header(ServiceApi.PLACE_COMPANY_P_ID, companyCode)
                        .header(ServiceApi.App_KEY, appKey)
                        .method(originReq.method(), originReq.body())
                        .build();
                return chain.proceed(req);
            }
        });

        if (BuildConfig.DEBUG) {//debug模式添加log
            builder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    okhttp3.Request originReq = chain.request();
                    Log.d(TAG, "正在请求：" + originReq.url() + "\n请求头：" + "originReq.headers()" + "\n请求体：" + originReq.body());
                    okhttp3.Response response = chain.proceed(originReq);
//                    Log.d(TAG, "获得响应：" + response.body());
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
    public void login(final String phone, final FtdLoginCallback callback) {
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
                        RequestBody requestBody = RequestBody.create(MediaType.parse(ServiceApi.JSON_MEDIA), json);
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
                        if (callback != null) {
                            callback.onSuccess();
                        }
                    }
                }, new ErrorConsumer(callback));
    }

    @SuppressLint("CheckResult")
//    public void pictureUpload(File file, @ServiceApi.Type int type) {
    public void pictureUpload(File file, int type) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        builder.addFormDataPart("faceTongueFlag", String.valueOf(type));
        builder.addFormDataPart("check_id", Util.getUUID());
        builder.addFormDataPart("scheme_flag", schemeId);
        builder.addFormDataPart("equCode", ServiceApi.MAC);
        String authorization = Util.generateAuthorization(appKey, this.appSecret, "OvationHealth/FaceTongueCheckServlet");
        service.picUpload(
                authorization,
                builder.build())
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

    @SuppressLint("CheckResult")
    private Single<FtdResponse<UploadResult>> picUpload(File file, int type) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        builder.addFormDataPart("check_id", Util.getUUID());
        builder.addFormDataPart("equCode", ServiceApi.MAC);
        builder.addFormDataPart("faceTongueFlag", String.valueOf(type));
        builder.addFormDataPart("faceTongueTag", String.valueOf(type));
        builder.addFormDataPart("mApi", "7");
        builder.addFormDataPart("scheme_flag", schemeId);


        builder.addFormDataPart("userId", user.getPhrId());
        builder.addFormDataPart("username", user.getMoble());
        builder.addFormDataPart("userType", "1");
//        builder.addFormDataPart("appkey", this.appKey);
        return service.picUpload1(user.getUuid(), builder.build());
    }

    private Disposable disposable;

    @SuppressLint("CheckResult")
    public void picUpload(File file1, File file2, File file3, final FtdPicUploadCallback callback) {
        if (TextUtils.isEmpty(schemeId)) {
            schemeId = Util.getUUID();
        } else {
            return;
        }
        disposable = Single.zip(picUpload(file1, ServiceApi.FACE), picUpload(file2, ServiceApi.TONGUE_TOP), picUpload(file3, ServiceApi.TONGUE_BOTTOM), new Function3<FtdResponse<UploadResult>, FtdResponse<UploadResult>, FtdResponse<UploadResult>, Conclusion>() {
            @Override
            public Conclusion apply(FtdResponse<UploadResult> faceResult, FtdResponse<UploadResult> tongueTopResult, FtdResponse<UploadResult> tongueBottomResult) throws Exception {
                if (faceResult == null || faceResult == null || tongueBottomResult == null) {

                }
                if (faceResult.getCode() != 1000 || tongueTopResult.getCode() != 1000 || tongueBottomResult.getCode() != 1000) {

                }
                Conclusion conclusion = new Conclusion();
                conclusion.setFaceResult(faceResult.getData());
                conclusion.setTongueTopResult(tongueTopResult.getData());
                conclusion.setTongueBottomfaceResult(tongueBottomResult.getData());
                return conclusion;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Conclusion>() {
                    @Override
                    public void accept(Conclusion result) throws Exception {
                        if (callback != null) {
                            callback.onSuccess();
                        }
                    }
                }, new ErrorConsumer(callback));
    }

    /**
     * 停止上传
     */
    public void stopUpload() {
        if (disposable != null && !disposable.isDisposed()) {
            this.disposable.dispose();
        }
    }

    @SuppressLint("CheckResult")
    public void getQuestion(final FtdQuestionListCallback callback) {

        GetQuestionParam param = new GetQuestionParam(user, this.schemeId);
        String json = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse(ServiceApi.JSON_MEDIA), json);
        service.getQuestion(
                user.getUuid(),
                requestBody
        )
                .subscribeOn(Schedulers.io())
                .map(new Function<FtdResponse<AskBean>, AskBean>() {
                    @Override
                    public AskBean apply(FtdResponse<AskBean> response) throws Exception {
                        if (response.getCode() != 1000 || response.getData() == null) {
                            throw new Exception();
                        }
                        return response.getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AskBean>() {
                    @Override
                    public void accept(AskBean s) throws Exception {
                        if (callback != null) {
                            callback.onSuccess(s);
                        }
                    }
                }, new ErrorConsumer(callback));
    }

    @SuppressLint("CheckResult")
    public void submitAnswer(List<QuestionBean> questionList1, List<QuestionBean> questionList2, String traceId1, String traceId2, final FtdSubmitCallback callback) {
        String westernMedicineInfo = gson.toJson(questionList1);
        String constitutionWesternMedicineInfo = gson.toJson(questionList2);
        SubmitAnswerParam param = new SubmitAnswerParam(
                user.getPhrId(),
                user.getMoble(),
                this.schemeId,
                traceId1,
                westernMedicineInfo,
                constitutionWesternMedicineInfo,
                traceId2
        );
        String json = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse(ServiceApi.JSON_MEDIA), json);
        service.submitAnswer(
                user.getUuid(),
                requestBody
        )
                .subscribeOn(Schedulers.io())
                .map(new Function<FtdResponse<AskBean>, AskBean>() {
                    @Override
                    public AskBean apply(FtdResponse<AskBean> response) throws Exception {
                        if (response == null || response.getCode() != 1000 || response.getData() == null) {
                            throw new Exception();
                        }
                        return response.getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AskBean>() {
                    @Override
                    public void accept(AskBean response) throws Exception {
                        if (callback != null) {
                            callback.onSuccess(response);
                        }
                    }
                }, new ErrorConsumer(callback));

    }

    /**
     * 获取报告
     */
    @SuppressLint("CheckResult")
    public void getLastRecord(long seqNo, final FtdLastReportCallback callback) {
        GetReportParam param = new GetReportParam(user.getPhrId(), seqNo);
        String json = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse(ServiceApi.JSON_MEDIA), json);
        service.getLastReport(user.getUuid(), requestBody)
                .subscribeOn(Schedulers.io())
                .map(new Function<FtdResponse<ReportBean>, ReportBean>() {
                    @Override
                    public ReportBean apply(FtdResponse<ReportBean> response) throws Exception {
                        if (response == null || response.getCode() != 1000 || response.getData() == null) {
                            throw new Exception();
                        }
                        return response.getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReportBean>() {
                    @Override
                    public void accept(ReportBean response) throws Exception {
                        if (callback != null) {
                            callback.onSuccess(response);
                        }
                    }
                }, new ErrorConsumer(callback));
    }

    @SuppressLint("CheckResult")
    public void getAnalyzer(List<ReportBean.UrBean> urList, final FtdGetAnaylzerCallback callback) {
        StringBuffer diseaseIds = new StringBuffer();
        int size = urList.size();
        for (int i = 0; i < size; i++) {
            diseaseIds.append(urList.get(i).getDiseaseId());
            diseaseIds.append(",");
        }
        GetAnalyzerParam param = new GetAnalyzerParam(diseaseIds.toString());
        String json = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse(ServiceApi.JSON_MEDIA), json);
        service.getAnalyzer(user.getUuid(), requestBody)
                .subscribeOn(Schedulers.io())
                .map(new Function<FtdResponse<AnalyzeResultBean>, AnalyzeResultBean>() {
                    @Override
                    public AnalyzeResultBean apply(FtdResponse<AnalyzeResultBean> response) throws Exception {
                        if (response == null || response.getCode() != 1000 || response.getData() == null) {
                            throw new Exception();
                        }
                        return response.getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AnalyzeResultBean>() {
                    @Override
                    public void accept(AnalyzeResultBean response) throws Exception {
                        if (callback != null) {
                            callback.onSuccess(response);
                        }
                    }
                }, new ErrorConsumer(callback));
    }

    /**
     * 获取健康微语
     *
     * @return
     */
    @SuppressLint("CheckResult")
    public void getMicroTip(final FtdMicroTipCallback callback) {
        service.getMicroTip(user.getPhrId())
                .subscribeOn(Schedulers.io())
                .map(new Function<FtdResponse<MicroTipBean>, MicroTipBean>() {
                    @Override
                    public MicroTipBean apply(FtdResponse<MicroTipBean> response) throws Exception {
                        if (response == null || response.getCode() != 1000 || response.getData() == null) {
                            throw new Exception();
                        }
                        return response.getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MicroTipBean>() {
                    @Override
                    public void accept(MicroTipBean response) throws Exception {
                        if (callback != null) {
                            callback.onSuccess(response);
                        }
                    }
                }, new ErrorConsumer(callback));
    }

    private static class ErrorConsumer implements Consumer<Throwable> {
        private BaseCallback callback;

        public ErrorConsumer(BaseCallback callback) {
            this.callback = callback;
        }

        @Override
        public void accept(Throwable throwable) throws Exception {
            if (callback != null) {
                if (throwable instanceof FtdException) {
                    callback.onError((FtdException) throwable);
                } else {
                    callback.onError(new FtdException(0));
                }
            }
        }
    }
}
