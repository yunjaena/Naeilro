package com.koreatech.naeilro.network.service;

import com.koreatech.core.network.Xml;
import com.koreatech.naeilro.network.entity.tour.TourList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TourService {
    String TOUR_INFO = "rest/KorService/areaBasedList";
    String TOUR_COMMON_INFO = "rest/KorService/detailCommon";
    String TOUR_DETAIL_INFO = "rest/KorService/detailInfo";
    String TOUR_DETAIL_INTRODUCE = "rest/KorService/detailIntro";
    String TOUR_DETAIL_IMAGE_INFO = "rest/KorService/detailImage";


    @GET(TOUR_INFO)
    @Xml
    Observable<TourList> getTourList(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId, @Query("numOfRows") int numOfRows,
                                     @Query("pageNo") int pageNo, @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp,
                                     @Query("arrange") String arrange, @Query("listYN") String listYN);

    @GET(TOUR_INFO)
    @Xml
    Observable<TourList> getTourCategoryList(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId, @Query("numOfRows") int numOfRows,
                                             @Query("pageNo") int pageNo, @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp,
                                             @Query("arrange") String arrange, @Query("listYN") String listYN, @Query("areaCode") int areaCode, @Query("sigunguCode") int sigunguCode);


    @GET(TOUR_COMMON_INFO)
    @Xml
    Observable<TourList> getTourCommonInfo(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId, @Query("contentId") int contentId
            , @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp, @Query("defaultYN") String defaultYN,
                                           @Query("firstImageYN") String firstImageYN, @Query("areacodeYN") String areacodeYN, @Query("addrinfoYN") String addrinfoYN,
                                           @Query("mapinfoYN") String mapinfoYN, @Query("overviewYN") String overviewYN);

    @GET(TOUR_DETAIL_INFO)
    @Xml
    Observable<TourList> getTourDetailInfo(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId, @Query("contentId") int contentId
            , @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp, @Query("listYN") String listYN);

    @GET(TOUR_DETAIL_INTRODUCE)
    @Xml
    Observable<TourList> getTourDetailIntroduce(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId, @Query("contentId") int contentId
            , @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp, @Query("introYN") String introYN);

    @GET(TOUR_DETAIL_IMAGE_INFO)
    @Xml
    Observable<TourList> getTourImageInfo(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId, @Query("contentId") int contentId
            , @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp, @Query("imageYN") String imageYN);
}
