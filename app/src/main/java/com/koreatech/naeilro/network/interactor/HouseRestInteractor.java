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
    String apiKey = NaeilroApplication.getDataApiKey();

    @Override
    public void getHouseTotalCount(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN) {
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

    @Override
    public void getHouseCommonInfo(ApiCallback apiCallback, int contentTypeId, int contentId, String MobileApp) {
        List<HouseInfo> houseItems = new ArrayList<>();
        Observable<HouseInfoList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(HouseService.class).
                getHouseCommonInfo(apiKey, contentTypeId, contentId, mobileOS, MobileApp, "Y", "Y","Y","Y","Y")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<HouseInfoList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HouseInfoList houseInfoList) {
                if (houseInfoList != null) {
                    apiCallback.onSuccess(houseInfoList.getHouseBodyList().get(0).getHouseInfoItemList().get(0).getHouseInfoList().get(0));
                } else
                    apiCallback.onFailure(new Throwable("fail read houseList"));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getHouseIntroInfo(ApiCallback apiCallback, int contentTypeId, int contentId, String MobileApp) {
        Log.e("inteeractor", "getHouseIntroInfo");
        Observable<HouseInfoList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(HouseService.class).
                getHouseIntroInfo(apiKey, contentTypeId, contentId, mobileOS, MobileApp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<HouseInfoList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HouseInfoList houseInfoList) {
                if (houseInfoList != null) {
                    apiCallback.onSuccess(houseInfoList.getHouseBodyList().get(0).getHouseInfoItemList().get(0).getHouseInfoList().get(0));
                } else
                    apiCallback.onFailure(new Throwable("fail read houseList"));
            }

            @Override
            public void onError(Throwable e) {
                    Log.e("fail", e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void getImageItems(ApiCallback apiCallback, int contentId, String MobileOs, String MobileApp) {
        Observable<HouseInfoList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(HouseService.class).
                getHouseImageInfo(apiKey, 32, contentId, mobileOS, MobileApp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<HouseInfoList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HouseInfoList houseInfoList) {
                List<HouseInfo> houseItems = new ArrayList<>();
                if(houseInfoList.getHouseBodyList().get(0).getHouseInfoItemList().get(0).getHouseInfoList() != null){
                    houseItems.addAll(houseInfoList.getHouseBodyList().get(0).getHouseInfoItemList().get(0).getHouseInfoList());
                    apiCallback.onSuccess(houseItems);
                }else{
                    apiCallback.onFailure(new Throwable("fail read houseImage"));
                }
            }

            @Override
            public void onError(Throwable e) {
                apiCallback.onFailure(new Throwable("none"));
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
