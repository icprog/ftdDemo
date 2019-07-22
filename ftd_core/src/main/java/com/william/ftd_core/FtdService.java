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
import com.william.ftd_core.param.GetAnalyzerParam;
import com.william.ftd_core.param.GetQuestionParam;
import com.william.ftd_core.param.GetReportParam;
import com.william.ftd_core.param.GetTendencyParam;
import com.william.ftd_core.param.LoginParam;
import com.william.ftd_core.param.SubmitAnswerParam;

import io.reactivex.Observable;
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
     * @param param
     * @return
     */
    @POST(ServiceApi.LOGIN)
    Single<FtdResponse<User>> login(@Body LoginParam param);

    /**
     * 上传图片
     *
     * @param files
     * @return
     */
    @POST(ServiceApi.PIC_UPLOAD1)
    Single<FtdResponse<UploadResult>> picUpload(
//            @Header(ServiceApi.LK_TOKEN) String lkToken,
            @Body RequestBody files);

    /**
     * 获取题目
     *
     * @param lkToken
     * @return
     */
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(ServiceApi.GET_QUESTION)
    Single<FtdResponse<AskBean>> getQuestion(
//            @Header(ServiceApi.LK_TOKEN) String lkToken,
            @Body GetQuestionParam param
            );

    /**
     * 提交答案
     *
     * @param lkToken
     * @return
     */
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(ServiceApi.SUBMIT_ANSWER)
    Single<FtdResponse<AskBean>> submitAnswer(
//            @Header(ServiceApi.LK_TOKEN) String lkToken,
            @Body SubmitAnswerParam param
    );

    /**
     * 获取最后一次报告
     *
     * @param lkToken
     * @return
     */
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(ServiceApi.GET_LAST_REPORT)
    Single<FtdResponse<ReportBean>> getLastReport(
//            @Header(ServiceApi.LK_TOKEN) String lkToken,
            @Body GetReportParam param
    );

    /**
     * 获取分析结果
     * @param lkToken
     * @return
     */
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(ServiceApi.GET_HEALTH_ANALYZER)
    Single<FtdResponse<AnalyzeResultBean>> getAnalyzer(
//            @Header(ServiceApi.LK_TOKEN) String lkToken,
            @Body GetAnalyzerParam param
    );

//    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(ServiceApi.GET_TENDENCY)
    Single<FtdResponse<TendencyResult>> getTendency(
//            @Header(ServiceApi.LK_TOKEN) String lkToken,
            @Body GetTendencyParam param
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
