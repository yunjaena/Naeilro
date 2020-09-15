package com.koreatech.naeilro.network.service;

import com.koreatech.core.network.Xml;
import com.koreatech.naeilro.network.entity.traincitycode.TrainCityList;
import com.koreatech.naeilro.network.entity.trainsearch.TrainSearchList;
import com.koreatech.naeilro.network.entity.trainstaion.TrainStationList;
import com.koreatech.naeilro.network.entity.weather.TrainArrivalDepartInfo;
import com.koreatech.naeilro.network.entity.traininfo.TrainList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TrainService {
    String TRAIN_ARRIVAL_DEPART_INFO_PATH = "getStrtpntAlocFndTrainInfo";
    String TRAIN_INFO = "getVhcleKndList";
    String TRAIN_CITY_INFO = "getCtyCodeList";
    String TRAIN_STATION_INFO = "getCtyAcctoTrainSttnList";
    String TRAIN_SEARCH = "getStrtpntAlocFndTrainInfo";

    /**
     * @param serviceKey     서비스 키
     * @param numOfRows      한 페이지 결과 수
     * @param pageNo         페이지 번호
     * @param depPlaceId     출발지ID
     * @param arrPlaceId     도착지ID
     * @param depPlandTime   출발일
     * @param trainGradeCode 차량종류코드
     * @return
     */
    @GET(TRAIN_ARRIVAL_DEPART_INFO_PATH)
    @Xml
    Observable<TrainArrivalDepartInfo> getTrainArrivalDepartInfo(@Query("serviceKey") String serviceKey, @Query("numOfRows") int numOfRows,
                                                                 @Query("pageNo") int pageNo, @Query("depPlaceId") String depPlaceId,
                                                                 @Query("arrPlaceId") String arrPlaceId, @Query("depPlandTime") String depPlandTime,
                                                                 @Query("trainGradeCode") String trainGradeCode);

    /**
     * @param serviceKey 서비스 키
     * @return
     */
    @GET(TRAIN_INFO)
    @Xml
    Observable<TrainList> getTrainList(@Query("serviceKey") String serviceKey);

    @GET(TRAIN_CITY_INFO)
    @Xml
    Observable<TrainCityList> getTrainCityList(@Query("serviceKey") String serviceKey);

    @GET(TRAIN_STATION_INFO)
    @Xml
    Observable<TrainStationList> getTrainStationList(@Query("serviceKey") String serviceKey, @Query("numOfRows") int numOfRows, @Query("pageNo") int pageNo, @Query("cityCode") String cityCode);

    @GET(TRAIN_SEARCH)
    @Xml
    Observable<TrainSearchList> getTrainSearchList(@Query("serviceKey") String serviceKey, @Query("numOfRows") int numOfRows, @Query("pageNo") int pageNo, @Query("depPlaceId") String depPlaceId, @Query("arrPlaceId") String arrPlaceId, @Query("depPlandTime") String depPlandTime, @Query("trainGradeCode") String trainGradeCode);
}
