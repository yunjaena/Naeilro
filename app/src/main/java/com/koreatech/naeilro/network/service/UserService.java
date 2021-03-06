package com.koreatech.naeilro.network.service;

import com.koreatech.core.network.Json;
import com.koreatech.naeilro.network.entity.user.EnrollObject;
import com.koreatech.naeilro.network.entity.user.Token;
import com.koreatech.naeilro.network.entity.user.UserInfo;

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
    String USER_INFO = "/nailo/member/member-info.php";
    String USER_LOGOUT = "/nailo/member/logout.php";
    String USER_DEACTIVATE_ACCOUNT = "/nailo/member/withdrawal.php";
    String USER_CHANGE_PASSWORD = "/nailo/member/change-pw.php";

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

    @POST(USER_INFO)
    @Json
    Observable<UserInfo> getUserInfo(@Header("Authorization") String auth);

    @POST(USER_LOGOUT)
    @Json
    Observable<UserInfo> logOut(@Header("Authorization") String auth);

    @POST(USER_DEACTIVATE_ACCOUNT)
    @Json
    Observable<UserInfo> deactivateAccount(@Header("Authorization") String auth);

    @POST(USER_CHANGE_PASSWORD)
    @Json
    Observable<UserInfo> changePassword(@Header("Authorization") String auth, @Body RequestBody requestBody);
}
