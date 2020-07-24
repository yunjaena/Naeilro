package com.koreatech.naeilro.network.interactor;

import android.util.Log;

import com.bumptech.glide.load.HttpException;
import com.koreatech.core.network.ApiCallback;
import com.koreatech.core.network.KoreanTourRetrofitManager;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.network.entity.tour.TourInfo;
import com.koreatech.naeilro.network.entity.tour.TourList;
import com.koreatech.naeilro.network.service.TourService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TourRestInteractor implements TourInteractor {
    public static final String TAG = "TourRestInteractor";
    public final String MOBILE_OS = "AND";
    public final int CONTENT_ID = 12;

    @Override
    public void getTourItems(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN) {
        List<TourInfo> tourInfoList = new ArrayList<>();
        String apiKey = NaeilroApplication.getDataApiKey();
        Observable<TourList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(TourService.class).
                getTourList(apiKey, CONTENT_ID, numOfRows, pageNo, MOBILE_OS, MobileApp, arrange, listYN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<TourList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TourList tourList) {
                if (tourList != null && tourList.getTourInfoBodyList().get(0).getTourInfoItemList().get(0).getTourInfoList() != null) {
                    tourInfoList.addAll(tourList.getTourInfoBodyList().get(0).getTourInfoItemList().get(0).getTourInfoList());
                } else
                    apiCallback.onFailure(new Throwable("fail read tourList"));
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof HttpException) {
                    Log.d(TAG, ((HttpException) e).getStatusCode() + " ");
                }
                apiCallback.onFailure(e);
            }

            @Override
            public void onComplete() {
                apiCallback.onSuccess(tourInfoList);
            }
        });

    }

    @Override
    public void getTourCategoryItems(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN, int areaCode, int sigunguCode) {
        List<TourInfo> tourInfoList = new ArrayList<>();
        String apiKey = NaeilroApplication.getDataApiKey();
        Observable<TourList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(TourService.class).
                getTourCategoryList(apiKey, CONTENT_ID, numOfRows, pageNo, MOBILE_OS, MobileApp, arrange, listYN, areaCode, sigunguCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<TourList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TourList tourList) {
                if (tourList != null && tourList.getTourInfoBodyList().get(0).getTourInfoItemList().get(0).getTourInfoList() != null) {
                    tourInfoList.addAll(tourList.getTourInfoBodyList().get(0).getTourInfoItemList().get(0).getTourInfoList());
                } else
                    apiCallback.onFailure(new Throwable("fail read tourList"));
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof HttpException) {
                    Log.d(TAG, ((HttpException) e).getStatusCode() + " ");
                }
                apiCallback.onFailure(e);
            }

            @Override
            public void onComplete() {
                apiCallback.onSuccess(tourInfoList);
            }
        });

    }
}
