package com.koreatech.naeilro.network.service;

import com.koreatech.core.network.Json;
import com.koreatech.naeilro.network.entity.user.EnrollObject;
import com.koreatech.naeilro.network.entity.user.SignIn;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    String ENROLL_USER = "/nailo/member/register.php";
    String SIGN_IN_USER = "/nailo/member/login.php";

    @POST(ENROLL_USER)
    @Json
    Observable<EnrollObject> postEnroll(@Body RequestBody requestBody);

    @POST(SIGN_IN_USER)
    @Json
    Observable<SignIn> postSignIn(@Body RequestBody requestBody);

}
