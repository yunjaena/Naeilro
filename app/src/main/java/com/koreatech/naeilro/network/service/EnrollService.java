package com.koreatech.naeilro.network.service;

import com.google.common.net.MediaType;
import com.google.gson.JsonObject;
import com.koreatech.core.network.Json;
import com.koreatech.naeilro.network.entity.enroll.EnrollObject;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EnrollService {
    String ENROLL_USER = "/nailo/member/register.php";

    @POST(ENROLL_USER)
    @Json
    Observable<EnrollObject> postEnroll(@Body RequestBody requestBody);

}
