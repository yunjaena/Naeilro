package com.koreatech.naeilro.network.service;

import com.koreatech.core.network.Json;
import com.koreatech.naeilro.network.entity.user.EnrollObject;
import com.koreatech.naeilro.network.entity.user.Token;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {
    String ENROLL_USER = "/nailo/member/register.php";
    String SIGN_IN_USER = "/nailo/member/login.php";
    String REFRESH_ALL_TOKEN = "/nailo/member/refresh-Atoken.php";
    String REFRESH_TOKEN = "/nailo/member/get-Rtoken.php";

    @POST(ENROLL_USER)
    @Json
    Observable<EnrollObject> postEnroll(@Body RequestBody requestBody);

    @POST(SIGN_IN_USER)
    @Json
    Observable<Token> postSignIn(@Body RequestBody requestBody);

    @POST(REFRESH_ALL_TOKEN)
    @Json
    Call<Token> refreshToken(@Header("Authorization") String auth);

    @POST(REFRESH_TOKEN)
    @Json
    Observable<Token> getRefreshToken(@Body RequestBody requestBody);

}
