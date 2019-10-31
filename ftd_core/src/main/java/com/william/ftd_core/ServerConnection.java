package com.william.ftd_core;

import com.lk.mogaijson.JSON;
import com.lk.mogaijson.JSONObject;
import com.william.ftd_core.callback.FtdLastReportCallback;
import com.william.ftd_core.constant.ServiceApi;
import com.william.ftd_core.entity.FtdResponse;
import com.william.ftd_core.entity.QuestionBean;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;
import com.william.ftd_core.param.DiagnoseParam;
import com.william.ftd_core.param.GetQuestionParam;
import com.william.ftd_core.param.GetReportParam;
import com.william.ftd_core.param.HeaderParam;
import com.william.ftd_core.param.LoginParam;
import com.william.ftd_core.param.SubmitAnswerParam;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ServerConnection {

    private static final String TAG = ServerConnection.class.getSimpleName();
    private HeaderParam headerParam;

    public ServerConnection() {

    }

    public void updateUserToken(String token) {
        headerParam.lkToken = token;
    }


    /**
     * 初始化方法二（推荐）
     *
     * @param headerParam
     */
    public void init(HeaderParam headerParam) {
        this.headerParam = headerParam;
    }

    public static ServerConnection getInstance() {
        return Handler.instance;
    }

    private static class Handler {
        private static ServerConnection instance = new ServerConnection();
    }

    private OkHttpClient serviceClient = new OkHttpClient.Builder()
            .build();

    private OkHttpClient fileClient = new OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.MINUTES)
            .build();

    private Request createRequestBuilder(String path, RequestBody... requestBodies) {
        String reg = "https://qa.laikangland.com/%s";
        String url = String.format(reg, path);
        Request.Builder builder = new Request.Builder()
                .header(ServiceApi.APP_ID, headerParam.appId)
                .header(ServiceApi.APP_CODE, headerParam.appCode)
                .header(ServiceApi.PLACE_COMPANY_ID, headerParam.companyId)
                .header(ServiceApi.PLACE_COMPANY_P_ID, headerParam.companyPid)
                .header(ServiceApi.COMPANY_CODE, headerParam.companyCode)
                .header(ServiceApi.App_KEY, headerParam.appKey)
                .header(ServiceApi.LK_TOKEN, headerParam.lkToken)
                .url(url);
        if (requestBodies.length > 0) {
            builder.post(requestBodies[0]);
        }
        return builder.build();
    }

    /**
     * 初步检查网络请求结果
     *
     * @param response
     * @return
     * @throws IOException
     * @throws FtdException
     */
    public static <T> T checkResponse(Response response, Class<T> clazz) throws FtdException {
        if (!response.isSuccessful()) {
            throw new FtdException();
        }
        String responseBody = null;
        try {
            responseBody = response.body().string();
        } catch (IOException e) {
            throw new FtdException();
        }
        FtdResponse ftdResponse = JSON.parseObject(responseBody, FtdResponse.class);
        if (ftdResponse.getCode() != 1000) {
            throw new FtdException(ftdResponse.getMsg());
        }
        if (
                clazz == byte.class ||
                        clazz == short.class ||
                        clazz == int.class ||
                        clazz == long.class ||
                        clazz == float.class ||
                        clazz == double.class ||
                        clazz == boolean.class ||
                        clazz == char.class ||
                        clazz == String.class
        ) {
            return (T) (ftdResponse.getData());
        } else {
            JSONObject jb = (JSONObject) ftdResponse.getData();
            return jb.toJavaObject(clazz);
        }
    }

    private RequestBody createRequestBody(String json) {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
    }

    /**
     * 获取令牌
     *
     * @return
     */
    public Response getToken() throws FtdException {
        Request request = createRequestBuilder(ServiceApi.GET_THIRD_TOKEN);
        Call call = serviceClient.newCall(request);
        try {
            return call.execute();
        } catch (IOException e) {
            throw new FtdException();
        }
    }

    /**
     * 登陆
     *
     * @return
     */
    public Response login(LoginParam param) throws FtdException {
        String json = JSON.toJSONString(param);
        RequestBody requestBody = createRequestBody(json);
        Request request = createRequestBuilder(ServiceApi.LOGIN, requestBody);
        try {
            return serviceClient.newCall(request).execute();
        } catch (IOException e) {
            throw new FtdException();
        }
    }

    /**
     * 文件上传
     *
     * @return
     * @throws IOException
     */
    public Response upload(DiagnoseParam param) throws FtdException {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("file", param.getFile().getName(), RequestBody.create(MediaType.parse("image/*"), param.getFile()));
        builder.addFormDataPart("check_id", param.getCheck_id());
        builder.addFormDataPart("equCode", param.getEquCode());
        builder.addFormDataPart("faceTongueFlag", String.valueOf(param.getDiagnoseTag()));
        builder.addFormDataPart("faceTongueTag", String.valueOf(param.getDiagnoseTag()));
        builder.addFormDataPart("mApi", "7");
        builder.addFormDataPart("scheme_flag", param.getScheme_flag());
        builder.addFormDataPart("userId", param.getUserId());
        builder.addFormDataPart("username", param.getUsername());
        builder.addFormDataPart("userType", param.getUserType());
        Request request = createRequestBuilder(ServiceApi.PIC_UPLOAD, builder.build());
        try {
            return serviceClient.newCall(request).execute();
        } catch (IOException e) {
            throw new FtdException();
        }
    }

    /**
     * 获取健康微语
     *
     * @param user
     * @return
     * @throws FtdException
     */
    public Response getTip(User user) throws FtdException {
        Request request = createRequestBuilder(ServiceApi.GET_TIP + "?" + ServiceApi.USER_ID + "=" + user.getPhrId());
        try {
            return serviceClient.newCall(request).execute();
        } catch (IOException e) {
            throw new FtdException();
        }
    }

    /**
     * 获取问诊问题列表
     *
     * @param user
     * @param schemeId
     * @return
     * @throws FtdException
     */
    public Response getQuestion(User user, String schemeId) throws FtdException {
        GetQuestionParam param = new GetQuestionParam(user, schemeId);
        String json = JSON.toJSONString(param);
        RequestBody requestBody = createRequestBody(json);
        Request request = createRequestBuilder(ServiceApi.GET_QUESTION, requestBody);
        try {
            return serviceClient.newCall(request).execute();
        } catch (IOException e) {
            throw new FtdException();
        }
    }

    /**
     * 提交问诊答案
     *
     * @param user
     * @param questionList1
     * @param questionList2
     * @param traceId1
     * @param traceId2
     * @param schemeId
     * @return
     * @throws FtdException
     */
    public Response submitAnswer(User user, List<QuestionBean> questionList1, List<QuestionBean> questionList2, String traceId1, String traceId2, String schemeId) throws FtdException {
        String westernMedicineInfo = JSON.toJSONString(questionList1);
        String constitutionWesternMedicineInfo = JSON.toJSONString(questionList2);
        SubmitAnswerParam param = new SubmitAnswerParam(
                user.getPhrId(),
                user.getMoble(),
                schemeId,
                traceId1,
                westernMedicineInfo,
                constitutionWesternMedicineInfo,
                traceId2
        );
        String json = JSON.toJSONString(param);
        RequestBody requestBody = createRequestBody(json);
        Request request = createRequestBuilder(ServiceApi.SUBMIT_ANSWER, requestBody);
        try {
            return serviceClient.newCall(request).execute();
        } catch (IOException e) {
            throw new FtdException();
        }
    }

    /**
     * 获取检测记录
     * @param user
     * @param seqNO
     * @return
     * @throws FtdException
     */
    public Response getRecoedBySeqNO(User user, long seqNO) throws FtdException {
        GetReportParam param = new GetReportParam(user.getPhrId(), seqNO);
        String json = JSON.toJSONString(param);
        RequestBody requestBody = createRequestBody(json);
        Request request = createRequestBuilder(ServiceApi.GET_LAST_REPORT, requestBody);
        try {
            return serviceClient.newCall(request).execute();
        } catch (IOException e) {
            throw new FtdException();
        }
    }
}
