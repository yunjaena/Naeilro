package com.koreatech.naeilro.network.interactor;

import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.core.network.TrainRetrofitManager;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.network.entity.traincitycode.TrainCityList;
import com.koreatech.naeilro.network.entity.traininfo.TrainList;
import com.koreatech.naeilro.network.service.TrainService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class TrainRestInteractor implements TrainInteractor {
    public static final String TAG  = "TrainRestInteractor";

    @Override
    public void getTrainList(ApiCallback apiCallback) {
        String apiKey = NaeilroApplication.getDataApiKey();
        Observable<TrainList> observable = TrainRetrofitManager.getInstance().getRetrofit().create(TrainService.class).
                getTrainList(apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<TrainList>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(final TrainList response) {
                if (response != null) {
                    apiCallback.onSuccess(response);
                } else {
                    apiCallback.onFailure(new Throwable("fail read train list info"));
                }
            }


            @Override
            public void onError(Throwable throwable) {
                if (throwable instanceof HttpException) {
                    Log.d(TAG, ((HttpException) throwable).code() + " ");
                }
                apiCallback.onFailure(throwable);
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void getTrainCityList(ApiCallback apiCallback) {
        String apiKey = NaeilroApplication.getDataApiKey();
        Observable<TrainCityList> observable = TrainRetrofitManager.getInstance().getRetrofit().create(TrainService.class).
                getTrainCityList(apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<TrainCityList>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(final TrainCityList response) {
                if (response != null) {
                    apiCallback.onSuccess(response);
                } else {
                    apiCallback.onFailure(new Throwable("fail read train city list info"));
                }
            }


            @Override
            public void onError(Throwable throwable) {
                if (throwable instanceof HttpException) {
                    Log.d(TAG, ((HttpException) throwable).code() + " ");
                }
                apiCallback.onFailure(throwable);
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
