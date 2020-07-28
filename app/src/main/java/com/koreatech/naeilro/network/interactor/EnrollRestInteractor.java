package com.koreatech.naeilro.network.interactor;

import com.google.gson.JsonObject;
import com.koreatech.core.network.ApiCallback;
import com.koreatech.core.network.UserRetrofitManager;
import com.koreatech.naeilro.network.entity.enroll.EnrollObject;
import com.koreatech.naeilro.network.service.EnrollService;

import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EnrollRestInteractor implements EnrollInteractor {
    @Override
    public void getAccept(ApiCallback apiCallback, String name, String pw, String email) {
        /*
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("name", name);
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("pw", pw);

         */
        EnrollObject jsonObject = new EnrollObject(name, email, pw);
        UserRetrofitManager.getInstance().getRetrofit().create(EnrollService.class).postEnroll(jsonObject)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EnrollObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EnrollObject enrollObject) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
