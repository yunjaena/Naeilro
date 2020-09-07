package com.koreatech.naeilro.network.interactor;

import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.core.network.KoreanTourRetrofitManager;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.network.entity.facility.Facility;
import com.koreatech.naeilro.network.entity.facility.FacilityInfoList;
import com.koreatech.naeilro.network.entity.restaurant.RestaurantInfo;
import com.koreatech.naeilro.network.entity.restaurant.RestaurantList;
import com.koreatech.naeilro.network.service.FacilityService;
import com.koreatech.naeilro.network.service.RestaurantService;
import com.koreatech.naeilro.util.DataAPIMessageUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class RestaurantRestInteractor implements RestaurantInteractor {
    private String mobileOS = "AND";
    public static final String TAG = "RestRestInteractor";
    private String apiKey = NaeilroApplication.getDataApiKey();

    @Override
    public void getRestaurantImtes(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN) {
        List<RestaurantInfo> restaurantInfoItems = new ArrayList<>();
        Observable<RestaurantList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(RestaurantService.class).
                getRestaurantList(apiKey, 39, listYN, numOfRows, pageNo, mobileOS, "nailro")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<RestaurantList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RestaurantList facilityInfoList) {
                if(facilityInfoList != null){
                    restaurantInfoItems.addAll(facilityInfoList.getRestaurantInfoBodyList().get(0).getRestaurantInfoItemList().get(0).getRestaurantInfoList());
                    apiCallback.onSuccess(restaurantInfoItems);
                }
                else{
                    apiCallback.onFailure(new Throwable("fail read RestaurantList"));
                }
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
    public void getRestaurantCategoryItems(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN, int areaCode, int sigunguCode) {
        List<RestaurantInfo> restaurantInfoItems = new ArrayList<>();
        Observable<RestaurantList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(RestaurantService.class).
                getRestaurantCategoryList(apiKey, 39, areaCode,sigunguCode, listYN, mobileOS, "nailro",arrange, numOfRows, pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<RestaurantList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RestaurantList facilityInfoList) {
                if(facilityInfoList != null){
                    restaurantInfoItems.addAll(facilityInfoList.getRestaurantInfoBodyList().get(0).getRestaurantInfoItemList().get(0).getRestaurantInfoList());
                    apiCallback.onSuccess(restaurantInfoItems);
                }
                else{
                    apiCallback.onFailure(new Throwable("fail read RestaurantList"));
                }
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
    public void getCommonItems(ApiCallback apiCallback, int contentId, String MobileOs, String MobileApp) {

    }

    @Override
    public void getImageItems(ApiCallback apiCallback, int contentId, String MobileOs, String MobileApp) {
        List<RestaurantInfo> restaurantInfoItems = new ArrayList<>();
        Observable<RestaurantList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(RestaurantService.class).
                getRestaurantImageInfo(apiKey, 39, contentId, mobileOS, MobileApp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<RestaurantList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RestaurantList facilityInfoList) {
                if(facilityInfoList != null){
                    restaurantInfoItems.addAll(facilityInfoList.getRestaurantInfoBodyList().get(0).getRestaurantInfoItemList().get(0).getRestaurantInfoList());
                    apiCallback.onSuccess(restaurantInfoItems);
                }
                else{
                    apiCallback.onFailure(new Throwable("fail read RestaurantList"));
                }
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
    public void getDetailItems(ApiCallback apiCallback, int contentId, String MobileOs, String MobileApp) {
        List<RestaurantInfo> restaurantInfoItems = new ArrayList<>();
        Observable<RestaurantList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(RestaurantService.class).
                getRestaurantDetailnfo(apiKey, 39, contentId, mobileOS, MobileApp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<RestaurantList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RestaurantList facilityInfoList) {
                if(facilityInfoList != null){
                    restaurantInfoItems.addAll(facilityInfoList.getRestaurantInfoBodyList().get(0).getRestaurantInfoItemList().get(0).getRestaurantInfoList());
                    apiCallback.onSuccess(restaurantInfoItems);
                }
                else{
                    apiCallback.onFailure(new Throwable("fail read Restaurant List"));
                }
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
