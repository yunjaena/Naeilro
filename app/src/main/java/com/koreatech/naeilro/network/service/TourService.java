package com.koreatech.naeilro.network.service;

import com.koreatech.core.network.Xml;
import com.koreatech.naeilro.network.entity.tour.TourList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TourService {
    String TOUR_INFO = "rest/KorService/areaBasedList";


    @GET(TOUR_INFO)
    @Xml
    Observable<TourList> getTourList(@Query("serviceKey") String serviceKey, @Query("numOfRows") int numOfRows,
                                     @Query("pageNo") int pageNo, @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp,
                                     @Query("arrange") String arrange, @Query("listYN") String listYN);

    @GET(TOUR_INFO)
    @Xml
    Observable<TourList> getTourCategoryList(@Query("serviceKey") String serviceKey, @Query("numOfRows") int numOfRows,
                                             @Query("pageNo") int pageNo, @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp,
                                             @Query("arrange") String arrange, @Query("listYN") String listYN, @Query("areaCode") int areaCode, @Query("sigunguCode") int sigunguCode);
}
