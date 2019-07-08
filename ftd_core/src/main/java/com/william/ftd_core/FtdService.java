package com.william.ftd_core;

import com.william.ftd_core.constant.ServiceApi;
import com.william.ftd_core.entity.AnalyzeResultBean;
import com.william.ftd_core.entity.AskBean;
import com.william.ftd_core.entity.FtdResponse;
import com.william.ftd_core.entity.MicroTipBean;
import com.william.ftd_core.entity.ReportBean;
import com.william.ftd_core.entity.Result;
import com.william.ftd_core.entity.TendencyResult;
import com.william.ftd_core.entity.UploadResult;
import com.william.ftd_core.entity.User;

import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FtdService {

    /**
     * 获取令牌
     *
     * @return
     */
    @GET(ServiceApi.GET_THIRD_TOKEN)
    Single<FtdResponse<String>> getToken();

    /**
     * 登录
     *
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(ServiceApi.LOGIN)
    Single<FtdResponse<User>> login(@Body RequestBody body);

    /**
     * 上传图片
     *
     * @param files
     * @return
     */
    @POST(ServiceApi.PIC_UPLOAD)
    Single<Result> picUpload(
            @Header("Authorization") String Authorization,
            @Body RequestBody files);

    @POST(ServiceApi.PIC_UPLOAD1)
    Single<FtdResponse<UploadResult>> picUpload1(
//            @Header("Authorization") String Authorization,
            @Header(ServiceApi.LK_TOKEN) String lkToken,
            @Body RequestBody files);

    /**
     * 获取题目
     *
     * @param lkToken
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(ServiceApi.GET_QUESTION)
    Single<FtdResponse<AskBean>> getQuestion(
            @Header(ServiceApi.LK_TOKEN) String lkToken,
            @Body RequestBody body
    );

    /**
     * 提交答案
     *
     * @param lkToken
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(ServiceApi.SUBMIT_ANSWER)
    Single<FtdResponse<AskBean>> submitAnswer(
            @Header(ServiceApi.LK_TOKEN) String lkToken,
            @Body RequestBody body
    );

    /**
     * 获取最后一次报告
     *
     * @param lkToken
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(ServiceApi.GET_LAST_REPORT)
    Single<FtdResponse<ReportBean>> getLastReport(
            @Header(ServiceApi.LK_TOKEN) String lkToken,
            @Body RequestBody body
    );

    /**
     * 获取分析结果
     * @param lkToken
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(ServiceApi.GET_HEALTH_ANALYZER)
    Single<FtdResponse<AnalyzeResultBean>> getAnalyzer(
            @Header(ServiceApi.LK_TOKEN) String lkToken,
            @Body RequestBody body
    );

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(ServiceApi.GET_TENDENCY)
    Single<FtdResponse<TendencyResult>> getTendency(
            @Header(ServiceApi.LK_TOKEN) String lkToken,
            @Body RequestBody body
    );

    /**
     * 获取健康微语
     *
     * @param userId
     * @return
     */
    @GET(ServiceApi.GET_TIP)
    Single<FtdResponse<MicroTipBean>> getMicroTip(@Query(ServiceApi.USER_ID) String userId);
}
