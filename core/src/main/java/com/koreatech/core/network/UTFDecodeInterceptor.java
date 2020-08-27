package com.koreatech.core.network;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class UTFDecodeInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder();
        builder.addHeader("Content-Type","application/json; charset=utf-8");
        builder.addHeader("Accept","application/json; charset=utf-8");
        builder.method(original.method(),original.body());
        Request request = builder.build();
        Response response = chain.proceed(request);
        return response.newBuilder().body(ResponseBody.create(response.body().contentType() , URLDecoder.decode(response.body().string(),"utf-8"))).build();
    }
}
