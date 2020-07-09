package com.koreatech.naeilro.network.interactor;

import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.core.network.KoreanTourRetrofitManager;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.network.entity.event.Festival;
import com.koreatech.naeilro.network.entity.event.FestivalInfoList;
import com.koreatech.naeilro.network.entity.reports.Reports;
import com.koreatech.naeilro.network.entity.reports.ReportsInfoList;
import com.koreatech.naeilro.network.service.EventService;
import com.koreatech.naeilro.network.service.ReportsService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class ReportsRestInteractor implements ReportsInteractor {
    private String mobileOS = "AND";
    public static final String TAG = "ReportsRestInteractor";
    private String apiKey = NaeilroApplication.getDataApiKey();

    @Override
    public void getReportsImtes(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN) {
        List<Reports> reportsItems = new ArrayList<>();
        Observable<ReportsInfoList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(ReportsService.class).
                getReportsList(apiKey, 28, listYN, numOfRows, pageNo, mobileOS, "nailro")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<ReportsInfoList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ReportsInfoList reportsInfoList) {
                if(reportsInfoList != null){
                    reportsItems.addAll(reportsInfoList.getReportsBodyList().get(0).getReportsInfoItems().get(0).getReportsList());
                    apiCallback.onSuccess(reportsItems);
                }
                else {
                    apiCallback.onFailure(new Throwable("fail read ReportsList"));
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
    public void getRepostsCategoryItems(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN, int areaCode, int sigunguCode) {
        List<Reports> reportsItems = new ArrayList<>();
        Observable<ReportsInfoList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(ReportsService.class).
                getReportsCategoryList(apiKey, 28,areaCode, sigunguCode, listYN, mobileOS, "nailro", arrange, numOfRows, pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<ReportsInfoList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ReportsInfoList reportsInfoList) {
                if(reportsInfoList != null){
                    reportsItems.addAll(reportsInfoList.getReportsBodyList().get(0).getReportsInfoItems().get(0).getReportsList());
                    apiCallback.onSuccess(reportsItems);
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
        Observable<ReportsInfoList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(ReportsService.class).
                getReportsCommonInfo(apiKey, 28,contentId, mobileOS, "nailro", "Y", "Y", "Y", "Y","Y")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<ReportsInfoList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ReportsInfoList reportsInfoList) {
                if(reportsInfoList != null){
                    apiCallback.onSuccess(reportsInfoList.getReportsBodyList().get(0).getReportsInfoItems().get(0).getReportsList().get(0));
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

        Observable<ReportsInfoList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(ReportsService.class).
                getReportsImageInfo(apiKey, 28,contentId, mobileOS, MobileApp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<ReportsInfoList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ReportsInfoList reportsInfoList) {
                List<Reports> reportsItems = new ArrayList<>();
                if(reportsInfoList.getReportsBodyList().get(0).getReportsInfoItems().get(0).getReportsList() != null){
                    reportsItems.addAll(reportsInfoList.getReportsBodyList().get(0).getReportsInfoItems().get(0).getReportsList());
                    apiCallback.onSuccess(reportsItems);
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
        Observable<ReportsInfoList> observable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(ReportsService.class).
                getReportsDetailnfo(apiKey, 28,contentId, mobileOS, MobileApp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<ReportsInfoList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ReportsInfoList reportsInfoList) {
                List<Reports> reportsItems = new ArrayList<>();
                if(reportsInfoList.getReportsBodyList().get(0).getReportsInfoItems().get(0).getReportsList() != null){
                    reportsItems.addAll(reportsInfoList.getReportsBodyList().get(0).getReportsInfoItems().get(0).getReportsList());
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
