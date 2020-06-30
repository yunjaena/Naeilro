package com.koreatech.naeilro.network.interactor;

import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.core.network.KoreanTourRetrofitManager;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.network.entity.event.Festival;
import com.koreatech.naeilro.network.entity.event.FestivalInfoList;
import com.koreatech.naeilro.network.service.EventService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class FestivalRestInteractor implements FestivalInteractor {
    String mobileOS = "AND";
    public static final String TAG = "EventRestInteractor";
    @Override
    public void getFestivalImtes(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN) {
        List<Festival> festivalItems = new ArrayList<>();
        String apiKey = NaeilroApplication.getDataApiKey();
        Observable<FestivalInfoList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(EventService.class).
                getEventList(apiKey, numOfRows, pageNo, mobileOS, MobileApp, arrange, listYN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

       observable.subscribe(new Observer<FestivalInfoList>() {
           @Override
           public void onSubscribe(Disposable d) {

           }

           @Override
           public void onNext(FestivalInfoList festivalInfoList) {
                if(festivalInfoList != null){

                    festivalItems.addAll(festivalInfoList.getFestivalBodyList().get(0).getFestivalInfoItems().get(0).getFestivalList());
                    apiCallback.onSuccess(festivalItems);
                    Log.e("sdf","dfg");
                }else
                    apiCallback.onFailure(new Throwable("fail read houseList"));
           }

           @Override
           public void onError(Throwable e) {
               if (e instanceof HttpException) {
                   Log.d(TAG, ((HttpException) e).code() + " ");
               }
               Log.e("asdf", e.getMessage());
               apiCallback.onFailure(e);
           }

           @Override
           public void onComplete() {

           }
       });

    }

    @Override
    public void getFestivalCategoryItems(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN, int areaCode, int sigunguCode) {
        List<Festival> festivalItems = new ArrayList<>();
        String apiKey = NaeilroApplication.getDataApiKey();
        Observable<FestivalInfoList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(EventService.class).
                getEventCategoryList(apiKey, numOfRows, pageNo, mobileOS, MobileApp, arrange, listYN, areaCode, sigunguCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<FestivalInfoList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FestivalInfoList festivalInfoList) {
                if (festivalInfoList != null) {
                    festivalItems.addAll(festivalInfoList.getFestivalBodyList().get(0).getFestivalInfoItems().get(0).getFestivalList());
                    apiCallback.onSuccess(festivalItems);
                } else
                    apiCallback.onFailure(new Throwable("fail read houseList"));
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof HttpException) {
                    Log.d(TAG, ((HttpException) e).code() + " ");
                }
                apiCallback.onFailure(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
