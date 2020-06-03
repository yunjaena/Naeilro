package com.koreatech.naeilro.network.service;

import com.koreatech.core.network.Xml;
import com.koreatech.naeilro.network.entity.house.HouseInfo;
import com.koreatech.naeilro.network.entity.house.HouseInfoList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HouseService {
    String HOUSE_LIST_INFO = "rest/KorService/searchStay";

    @GET(HOUSE_LIST_INFO)
    @Xml
    Observable<HouseInfoList> getHouseList(@Query("serviceKey") String serviceKey, @Query("numOfRows") int numOfRows,
                                           @Query("pageNo") int pageNo, @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp,
                                           @Query("arrange") String arrange, @Query("listYN") String listYN);
    @GET(HOUSE_LIST_INFO)
    @Xml
    Observable<HouseInfoList> getHouseCategoryList(@Query("serviceKey") String serviceKey, @Query("numOfRows") int numOfRows,
                                           @Query("pageNo") int pageNo, @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp,
                                           @Query("arrange") String arrange, @Query("listYN") String listYN, @Query("areaCode") int areaCode, @Query("sigunguCode") int sigunguCode);
}
