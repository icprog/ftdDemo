package com.william.ftd_core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.william.ftd_core.callback.BaseCallback;
import com.william.ftd_core.callback.FtdGetAnaylzerCallback;
import com.william.ftd_core.callback.FtdLastReportCallback;
import com.william.ftd_core.callback.FtdLoginCallback;
import com.william.ftd_core.callback.FtdMicroTipCallback;
import com.william.ftd_core.callback.FtdPicUploadCallback;
import com.william.ftd_core.callback.FtdQuestionListCallback;
import com.william.ftd_core.callback.FtdSubmitCallback;
import com.william.ftd_core.callback.FtdTendencyCallback;
import com.william.ftd_core.constant.ServiceApi;
import com.william.ftd_core.entity.AnalyzeResultBean;
import com.william.ftd_core.entity.AskBean;
import com.william.ftd_core.entity.Conclusion;
import com.william.ftd_core.entity.FtdResponse;
import com.william.ftd_core.entity.MicroTipBean;
import com.william.ftd_core.entity.QuestionBean;
import com.william.ftd_core.entity.ReportBean;
import com.william.ftd_core.entity.TendencyResult;
import com.william.ftd_core.entity.UploadResult;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;
import com.william.ftd_core.param.GetAnalyzerParam;
import com.william.ftd_core.param.GetQuestionParam;
import com.william.ftd_core.param.GetReportParam;
import com.william.ftd_core.param.GetTendencyParam;
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
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class FtdClient {

    private static final String TAG = "FtdClient";

    private Retrofit retrofit;
    private FtdService service;

    private String appKey;
    private String appId;
    private String appCode;
    private String companyCode;
    private String appSecret;
    private String companyId;
    private String companyPid;
    private String phrAppKey;
    private String phrAppSecret;

    private User user;

    private String schemeId;

    private String lkToken = "";

    private static FtdClient INSTANCE = new FtdClient();

    public static FtdClient getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化方法一（不推荐）
     *
     * @param param
     */
    public void init(final InitParam param, Converter.Factory factory, JsonConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
        this.appId = param.getAppId();
        this.appKey = param.getAppKey();
        this.appSecret = param.getAppSecret();
        this.companyCode = param.getCompanyCode();
        this.companyId = param.getCompanyId();
        this.companyPid = param.getCompanyPid();
        this.phrAppKey = param.getPhrAppKey();
        this.phrAppSecret = param.getPhrAppSecret();

        initWebService(factory);
    }

    private JsonConverter jsonConverter;

    /**
     * 初始化方法二（推荐）
     *
     * @param context
     */
    public void init(Context context, Converter.Factory retrofitFactory, JsonConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "init: ", e);
            return;
        }

        appId = String.valueOf(appInfo.metaData.getInt("laiKang.appId"));
        appCode = String.valueOf(appInfo.metaData.getInt("laiKang.appCode"));
        appKey = appInfo.metaData.getString("laiKang.appKey");
        appSecret = appInfo.metaData.getString("laiKang.appSecret");
        companyId = String.valueOf(appInfo.metaData.getInt("laiKang.companyId"));
        companyPid = String.valueOf(appInfo.metaData.getInt("laiKang.companyPid"));
        companyCode = appInfo.metaData.getString("laiKang.companyCode");
        phrAppKey = appInfo.metaData.getString("laiKang.phrAppKey");
        phrAppSecret = appInfo.metaData.getString("laiKang.phrAppSecret");

        initWebService(retrofitFactory);
    }

    private void initWebService(Converter.Factory factory) {
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
                        .header(ServiceApi.APP_CODE, appCode)
                        .header(ServiceApi.PLACE_COMPANY_ID, companyId)
                        .header(ServiceApi.PLACE_COMPANY_P_ID, companyId)
                        .header(ServiceApi.COMPANY_CODE, companyCode)
                        .header(ServiceApi.App_KEY, appKey)
                        .header(ServiceApi.LK_TOKEN, lkToken)
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
                    Log.d(TAG, "获得响应：" + response.body());
                    return response;
                }
            });
        }
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(builder.build())
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        this.service = this.retrofit.create(FtdService.class);
    }

    public interface JsonConverter {
        String toJson(Object o);
    }

    /**
     * 登录面舌诊
     */
    public Disposable login(final String phone, final FtdLoginCallback callback) {
        return service.getToken()
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
                        return service.login(param);
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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        FtdClient.this.user = user;
                        lkToken = user.getUuid();
                        if (callback != null) {
                            callback.onSuccess();
                        }
                    }
                }, new ErrorConsumer(callback));
    }


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
        return service.picUpload(builder.build());
    }


    public Disposable picUpload(File file1, File file2, File file3, final FtdPicUploadCallback callback) {
        schemeId = Util.getUUID();
        return Single.zip(picUpload(file1, ServiceApi.FACE), picUpload(file2, ServiceApi.TONGUE_TOP), picUpload(file3, ServiceApi.TONGUE_BOTTOM), new Function3<FtdResponse<UploadResult>, FtdResponse<UploadResult>, FtdResponse<UploadResult>, Conclusion>() {
            @Override
            public Conclusion apply(FtdResponse<UploadResult> faceResult, FtdResponse<UploadResult> tongueTopResult, FtdResponse<UploadResult> tongueBottomResult) throws Exception {
                Conclusion conclusion = new Conclusion();
                conclusion.setFaceResult(faceResult);
                conclusion.setTongueTopResult(tongueTopResult);
                return conclusion;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Conclusion, Conclusion>() {
                    @Override
                    public Conclusion apply(Conclusion conclusion) throws Exception {
                        return conclusion;
                    }
                })
                .subscribe(new Consumer<Conclusion>() {
                    @Override
                    public void accept(Conclusion result) throws Exception {
                        if (callback != null) {
                            callback.onSuccess(result);
                        }
                    }
                }, new ErrorConsumer(callback));
    }

    /**
     * 获取问诊题目
     *
     * @param callback
     */
    public Disposable getQuestion(final FtdQuestionListCallback callback) {

        GetQuestionParam param = new GetQuestionParam(user, this.schemeId);
        return service.getQuestion(param)
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

    /**
     * 提交问诊答案
     *
     * @param questionList1
     * @param questionList2
     * @param traceId1
     * @param traceId2
     * @param callback
     */
    public Disposable submitAnswer(List<QuestionBean> questionList1, List<QuestionBean> questionList2, String traceId1, String traceId2, final FtdSubmitCallback callback) {
        String westernMedicineInfo = this.jsonConverter.toJson(questionList1);
        String constitutionWesternMedicineInfo = this.jsonConverter.toJson(questionList2);
        SubmitAnswerParam param = new SubmitAnswerParam(
                user.getPhrId(),
                user.getMoble(),
                this.schemeId,
                traceId1,
                westernMedicineInfo,
                constitutionWesternMedicineInfo,
                traceId2
        );
        return service.submitAnswer(param)
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
    public Disposable getRecordBySeqNo(long seqNo, final FtdLastReportCallback callback) {
        GetReportParam param = new GetReportParam(user.getPhrId(), seqNo);
        return service.getLastReport(param)
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

    /**
     * 获取分析结果
     *
     * @param urList
     * @param callback
     */

    public Disposable getAnalyzer(List<ReportBean.UrBean> urList, final FtdGetAnaylzerCallback callback) {
        if (urList == null) {
            return null;
        }
        StringBuffer diseaseIds = new StringBuffer();
        int size = urList.size();
        for (int i = 0; i < size; i++) {
            diseaseIds.append(urList.get(i).getDiseaseId());
            if (i < size - 1) {
                diseaseIds.append(",");
            }
        }
        GetAnalyzerParam param = new GetAnalyzerParam(diseaseIds.toString());
        return service.getAnalyzer(param)
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
     * 获取趋势分析
     *
     * @param callback
     * @return
     */
    public Disposable getTendency(final FtdTendencyCallback callback) {
        GetTendencyParam param = new GetTendencyParam();
        return service.getTendency(param)
                .subscribeOn(Schedulers.io())
                .map(new Function<FtdResponse<TendencyResult>, TendencyResult>() {
                    @Override
                    public TendencyResult apply(FtdResponse<TendencyResult> response) throws Exception {
                        if (response == null || response.getCode() != 1000 || response.getData() == null) {
                            throw new Exception();
                        }
                        return response.getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TendencyResult>() {
                    @Override
                    public void accept(TendencyResult response) throws Exception {
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

    public Disposable getMicroTip(final FtdMicroTipCallback callback) {
        return service.getMicroTip(user.getPhrId())
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


    public String getAppKey() {
        return appKey;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppCode() {
        return appCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getCompanyPid() {
        return companyPid;
    }

    public String getPhrAppKey() {
        return phrAppKey;
    }

    public String getPhrAppSecret() {
        return phrAppSecret;
    }
}
