package com.koreatech.naeilro.network.interactor;

import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.core.network.TrainRetrofitManager;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.network.entity.traincitycode.TrainCityList;
import com.koreatech.naeilro.network.entity.traininfo.TrainList;
import com.koreatech.naeilro.network.entity.trainsearch.TrainSearchInfo;
import com.koreatech.naeilro.network.entity.trainsearch.TrainSearchList;
import com.koreatech.naeilro.network.entity.trainstaion.TrainStationInfo;
import com.koreatech.naeilro.network.entity.trainstaion.TrainStationList;
import com.koreatech.naeilro.network.service.TrainService;
import com.koreatech.naeilro.util.DataAPIMessageUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class TrainRestInteractor implements TrainInteractor {
    public static final String TAG = "TrainRestInteractor";

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
                    if (DataAPIMessageUtil.isSuccess(response.getMessageList().get(0))) {
                        apiCallback.onSuccess(response);
                    } else
                        apiCallback.onFailure(new Throwable("fail read train list info"));
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
    public void getTrainStationList(ApiCallback apiCallback) {
        String apiKey = NaeilroApplication.getDataApiKey();
        List<TrainStationInfo> stationInfoList = new ArrayList<>();
        Observable<TrainCityList> trainCityListObservable = TrainRetrofitManager.getInstance().getRetrofit().create(TrainService.class).
                getTrainCityList(apiKey);

        trainCityListObservable.flatMapIterable(trainCityList -> trainCityList.getTrainCityInfoBodyList().get(0).getTrainCityInfoItemList().get(0).getTrainCityInfoList())
                .flatMap(trainCityInfo -> TrainRetrofitManager.getInstance().getRetrofit().create(TrainService.class).
                        getTrainStationList(apiKey, 10, 1, trainCityInfo.getCityCode()).map(trainStationList -> {
                    trainStationList.setCityCode(trainCityInfo.getCityCode());
                    if (!DataAPIMessageUtil.isSuccess(trainStationList.getMessageList().get(0))) {
                        throw new Exception("Can not get trainStation Data");
                    }
                    return trainStationList;
                })).
                flatMap(trainStationList -> {
                    int totalCount = trainStationList.getTrainStationInfoBodyList().get(0).getTotalCount();
                    int numOfRows = trainStationList.getTrainStationInfoBodyList().get(0).getNumOfRows();
                    int totalPage = (int) Math.ceil((double) totalCount / numOfRows) + 1;
                    List<Observable<TrainStationList>> trainObservableList = new ArrayList<>();
                    for (int i = 1; i < totalPage; i++) {
                        trainObservableList.add(TrainRetrofitManager.getInstance().getRetrofit().create(TrainService.class).getTrainStationList(apiKey, 10, i, trainStationList.getCityCode()).subscribeOn(Schedulers.io()));
                    }
                    return Observable.concat(trainObservableList);
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TrainStationList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TrainStationList trainStationList) {
                        stationInfoList.addAll(trainStationList.getTrainStationInfoBodyList().get(0).getTrainStationInfoItemList().get(0).getTrainStationInfoList());
                    }

                    @Override
                    public void onError(Throwable e) {
                        apiCallback.onFailure(new Throwable("Can not get station info"));
                    }

                    @Override
                    public void onComplete() {
                        apiCallback.onSuccess(stationInfoList);
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

    @Override
    public void getTrainSearchList(String depPlaceId, String arrPlaceId, String depPlandTime, String trainGradeCode, ApiCallback apiCallback) {
        String apiKey = NaeilroApplication.getDataApiKey();
        List<TrainSearchInfo> trainSearchInfoList = new ArrayList<>();

        Observable<TrainSearchList> trainSearchListObservable = TrainRetrofitManager.getInstance().getRetrofit().create(TrainService.class).
                getTrainSearchList(apiKey, 10, 1, depPlaceId, arrPlaceId, depPlandTime, trainGradeCode);


        trainSearchListObservable.map(trainSearchList -> trainSearchList.getTrainSearchInfoBodyList().get(0))
                .flatMap(trainSearchInfoBody -> {
                    int totalCount = trainSearchInfoBody.getTotalCount();
                    int numOfRows = trainSearchInfoBody.getNumOfRows();
                    int totalPage = (int) Math.ceil((double) totalCount / numOfRows) + 1;
                    List<Observable<TrainSearchList>> trainObservableList = new ArrayList<>();
                    for (int i = 1; i < totalPage; i++) {
                        trainObservableList.add(TrainRetrofitManager.getInstance().getRetrofit().create(TrainService.class).
                                getTrainSearchList(apiKey, 10, i, depPlaceId, arrPlaceId, depPlandTime, trainGradeCode));
                    }
                    return Observable.concat(trainObservableList);
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TrainSearchList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TrainSearchList trainSearchList) {
                        trainSearchInfoList.addAll(trainSearchList.getTrainSearchInfoBodyList().get(0).getTrainSearchInfoItemList().get(0).getTrainSearchInfoList());
                    }

                    @Override
                    public void onError(Throwable e) {
                        apiCallback.onFailure(new Throwable("Can not get train search info"));
                    }

                    @Override
                    public void onComplete() {
                        apiCallback.onSuccess(trainSearchInfoList);
                    }
                });
    }
}
