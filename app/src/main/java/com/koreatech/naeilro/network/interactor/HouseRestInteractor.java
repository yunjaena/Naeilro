package com.koreatech.naeilro.network.interactor;

import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.core.network.KoreanTourRetrofitManager;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.network.entity.house.HouseInfo;
import com.koreatech.naeilro.network.entity.house.HouseInfoList;
import com.koreatech.naeilro.network.service.HouseService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class HouseRestInteractor implements HouseInteractor {
    public static final String TAG = "HouserRestInteractor";
    String mobileOS = "AND";

    @Override
    public void getHouseTotalCount(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN) {
        String apiKey = NaeilroApplication.getDataApiKey();
        Observable<HouseInfoList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(HouseService.class).
                getHouseList(apiKey, numOfRows, pageNo, mobileOS, MobileApp, arrange, listYN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<HouseInfoList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HouseInfoList houseInfoList) {
                if (houseInfoList != null)
                    apiCallback.onSuccess(houseInfoList.getHouseBodyList().get(0).getTotalCount());
                else
                    apiCallback.onFailure(new Throwable("fail read house total count"));
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

    @Override
    public void getHouseItems(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN) {
        List<HouseInfo> houseItems = new ArrayList<>();
        String apiKey = NaeilroApplication.getDataApiKey();
        Observable<HouseInfoList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(HouseService.class).
                getHouseList(apiKey, numOfRows, pageNo, mobileOS, MobileApp, arrange, listYN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<HouseInfoList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HouseInfoList houseInfoList) {
                if (houseInfoList != null) {
                    if (houseInfoList.getHouseBodyList().get(0).getHouseInfoItemList() != null) {
                    }

                    houseItems.addAll(houseInfoList.getHouseBodyList().get(0).getHouseInfoItemList().get(0).getHouseInfoList());
                    apiCallback.onSuccess(houseItems);
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


    @Override
    public void getHouseCategoryItems(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN, int areaCode, int sigunguCode) {
        List<HouseInfo> houseItems = new ArrayList<>();
        String apiKey = NaeilroApplication.getDataApiKey();
        Observable<HouseInfoList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(HouseService.class).
                getHouseCategoryList(apiKey, numOfRows, pageNo, mobileOS, MobileApp, arrange, listYN, areaCode, sigunguCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<HouseInfoList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HouseInfoList houseInfoList) {
                if (houseInfoList != null) {
                    if (houseInfoList.getHouseBodyList().get(0).getHouseInfoItemList() != null) {
                    }

                    houseItems.addAll(houseInfoList.getHouseBodyList().get(0).getHouseInfoItemList().get(0).getHouseInfoList());
                    apiCallback.onSuccess(houseItems);
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
