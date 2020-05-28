package com.koreatech.naeilro.network.interactor;

import com.koreatech.core.network.ApiCallback;

public interface TrainInteractor {
    void getTrainList(ApiCallback apiCallback);

    void getTrainCityList(ApiCallback apiCallback);

    void getTrainStationList(ApiCallback apiCallback);

    void getTrainSearchList(String depPlaceId, String arrPlaceId, String depPlandTime, String trainGradeCode, ApiCallback apiCallback);
}
