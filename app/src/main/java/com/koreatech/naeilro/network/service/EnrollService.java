package com.koreatech.naeilro.network.service;

import com.google.gson.JsonObject;
import com.koreatech.naeilro.network.entity.enroll.EnrollObject;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EnrollService {
    String ENROLL_USER = "/register.php";
    @POST(ENROLL_USER)
    Observable<EnrollObject> postEnroll(@Body EnrollObject jsonObject);
}
