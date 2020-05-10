package com.koreatech.naeilro.network.service;

import com.koreatech.core.network.Xml;
import com.koreatech.naeilro.network.entity.TrainArrivalDepartInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TrainService {
    String TRAIN_ARRIVAL_DEPART_INFO_PATH = "getStrtpntAlocFndTrainInfo";

    @GET(TRAIN_ARRIVAL_DEPART_INFO_PATH)
    @Xml
    Observable<TrainArrivalDepartInfo> getTrainArrivalDepartInfo(@Query("serviceKey")String serviceKey, @Query("numOfRows")int numOfRows,
                                                                 @Query("pageNo")int pageNo, @Query("depPlaceId") String depPlaceId, @Query("arrPlaceId")String arrPlaceId, @Query("depPlandTime")String depPlandTime, @Query("trainGradeCode") String trainGradeCode);

}
