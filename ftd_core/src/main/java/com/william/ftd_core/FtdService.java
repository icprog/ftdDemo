package com.william.ftd_core;

import com.william.ftd_core.constant.ServiceApi;
import com.william.ftd_core.entity.Result;
import com.william.ftd_core.entity.User;

import java.util.Map;

import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface FtdService {

//    @GET(ServiceApi.GET_THIRD_TOKEN)
//    Call<FtdResponse<String>> getToken();
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST(ServiceApi.LOGIN)
//    Call<FtdResponse<User>> login(@Body RequestBody body);

    @GET(ServiceApi.GET_THIRD_TOKEN)
    Single<FtdResponse<String>> getToken();

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(ServiceApi.LOGIN)
    Single<FtdResponse<User>> login(@Body RequestBody body);

    @Multipart
    @POST(ServiceApi.PIC_UPLOAD)
    Single<Result> picUpload(@PartMap Map<String, RequestBody> var2, @Part okhttp3.MultipartBody.Part var3);

    /**
     * 上传图片，用永康的做法
     * @param files
     * @return
     */
    @POST(ServiceApi.PIC_UPLOAD)
    Single<Result> picUpload(@Body RequestBody files);
}
