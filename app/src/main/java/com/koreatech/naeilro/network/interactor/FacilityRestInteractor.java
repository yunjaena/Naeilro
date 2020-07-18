package com.koreatech.naeilro.network.interactor;

import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.core.network.KoreanTourRetrofitManager;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.network.entity.facility.Facility;
import com.koreatech.naeilro.network.entity.facility.FacilityInfoList;
import com.koreatech.naeilro.network.entity.reports.Reports;
import com.koreatech.naeilro.network.entity.reports.ReportsInfoList;
import com.koreatech.naeilro.network.service.FacilityService;
import com.koreatech.naeilro.network.service.ReportsService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class FacilityRestInteractor implements FacilityInteractor {
    private String mobileOS = "AND";
    public static final String TAG = "ReportsRestInteractor";
    private String apiKey = NaeilroApplication.getDataApiKey();

    @Override
    public void getFacilityImtes(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN) {
        List<Facility> facilityItems = new ArrayList<>();
        Observable<FacilityInfoList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(FacilityService.class).
                getFacilityList(apiKey, 14, listYN, numOfRows, pageNo, mobileOS, "nailro")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<FacilityInfoList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FacilityInfoList facilityInfoList) {
                if(facilityInfoList != null){
                    facilityItems.addAll(facilityInfoList.getFacilityBodyList().get(0).getFacilityInfoItems().get(0).getFacilityList());
                    apiCallback.onSuccess(facilityItems);
                }
                else{
                    apiCallback.onFailure(new Throwable("fail read facilityList"));
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
    public void getFacilityCategoryItems(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN, int areaCode, int sigunguCode) {
        List<Facility> facilityItems = new ArrayList<>();
        Observable<FacilityInfoList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(FacilityService.class)
                .getFacilityCategoryList(apiKey, 14,areaCode, sigunguCode, listYN, mobileOS, "nailro", arrange, numOfRows, pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<FacilityInfoList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FacilityInfoList reportsInfoList) {
                if(reportsInfoList != null){
                    facilityItems.addAll(reportsInfoList.getFacilityBodyList().get(0).getFacilityInfoItems().get(0).getFacilityList());
                    apiCallback.onSuccess(facilityItems);
                }else {
                    apiCallback.onFailure(new Throwable("fail read reportslist"));
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
        Observable<FacilityInfoList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(FacilityService.class).
                getFacilityCommonInfo(apiKey, 14,contentId, mobileOS, "nailro", "Y", "Y", "Y", "Y","Y")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<FacilityInfoList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FacilityInfoList facilityInfoList) {
                if(facilityInfoList != null){
                    apiCallback.onSuccess(facilityInfoList.getFacilityBodyList().get(0).getFacilityInfoItems().get(0).getFacilityList().get(0));
                }else {
                    apiCallback.onFailure(new Throwable("fail read reportsCommonInfo"));
                }
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
    public void getImageItems(ApiCallback apiCallback, int contentId, String MobileOs, String MobileApp) {
        Observable<FacilityInfoList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(FacilityService.class).
                getFacilityImageInfo(apiKey, 14,contentId, mobileOS, MobileApp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<FacilityInfoList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FacilityInfoList facilityInfoList) {
                List<Facility> facilityItems = new ArrayList<>();
                if(facilityInfoList.getFacilityBodyList().get(0).getFacilityInfoItems().get(0).getFacilityList() != null){
                    facilityItems.addAll(facilityInfoList.getFacilityBodyList().get(0).getFacilityInfoItems().get(0).getFacilityList());
                    apiCallback.onSuccess(facilityItems);
                }else {
                    apiCallback.onFailure(new Throwable("fail read reportsImage"));
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

    @Override
    public void getDetailItems(ApiCallback apiCallback, int contentId, String MobileOs, String MobileApp) {
        Observable<FacilityInfoList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(FacilityService.class).
                getFacilityDetailnfo(apiKey, 14,contentId, mobileOS, MobileApp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<FacilityInfoList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FacilityInfoList facilityInfoList) {
                List<Facility> reportsItems = new ArrayList<>();
                if(facilityInfoList.getFacilityBodyList().get(0).getFacilityInfoItems().get(0).getFacilityList() != null){
                    reportsItems.addAll(facilityInfoList.getFacilityBodyList().get(0).getFacilityInfoItems().get(0).getFacilityList());
                    apiCallback.onSuccess(reportsItems);
                }else {
                    apiCallback.onFailure(new Throwable("fail read reportsDetailInfo"));
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
