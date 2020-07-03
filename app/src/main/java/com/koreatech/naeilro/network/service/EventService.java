package com.koreatech.naeilro.network.service;

import com.koreatech.core.network.Xml;
import com.koreatech.naeilro.network.entity.event.FestivalInfoList;
import com.koreatech.naeilro.network.entity.house.HouseInfoList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EventService {
    String EVENT_LIST_INFO = "rest/KorService/searchFestival";
    String EVENT_LIST_COMMON = "rest/KorService/detailCommon";
    String EVENT_LIST_INTRO = "rest/KorService/detailIntro";
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
    @GET(EVENT_LIST_COMMON)
    @Xml
    Observable<FestivalInfoList> getFestivalCommonList(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId, @Query("contentId") int contentId, @Query("MobileOS") String mobileOS,
                                                      @Query("MobileApp") String mobileApp, @Query("defaultYN") String defaultYN, @Query("firstImageYN") String firstImageYN, @Query("areacodeYN") String areacodeYN,
                                                      @Query("mapinfoYN") String mapinfoYN,@Query("overviewYN") String overviewYN);

    @GET(EVENT_LIST_INTRO)
    @Xml
    Observable<FestivalInfoList> getFestivalIntroList(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId, @Query("contentId") int contentId, @Query("MobileOS") String mobileOS,
                                                      @Query("MobileApp") String mobileApp);
}
