package com.koreatech.naeilro.network.service;

import com.koreatech.core.network.Xml;
import com.koreatech.naeilro.network.entity.event.FestivalInfoList;
import com.koreatech.naeilro.network.entity.house.HouseInfoList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EventService {
    String EVENT_LIST_INFO = "rest/KorService/searchFestival";

    @GET(EVENT_LIST_INFO)
    @Xml
    Observable<FestivalInfoList> getEventList(@Query("serviceKey") String serviceKey, @Query("numOfRows") int numOfRows,
                                              @Query("pageNo") int pageNo, @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp,
                                              @Query("arrange") String arrange, @Query("listYN") String listYN);
    @GET(EVENT_LIST_INFO)
    @Xml
    Observable<FestivalInfoList> getEventCategoryList(@Query("serviceKey") String serviceKey, @Query("numOfRows") int numOfRows,
                                                   @Query("pageNo") int pageNo, @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp,
                                                   @Query("arrange") String arrange, @Query("listYN") String listYN, @Query("areaCode") int areaCode, @Query("sigunguCode") int sigunguCode);
}
