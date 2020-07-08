package com.koreatech.naeilro.network.service;

import com.koreatech.core.network.Xml;
import com.koreatech.naeilro.network.entity.restaurant.RestaurantList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestaurantService {
    String RESTAURANT_SEARCH = "rest/KorService/searchStay";

    @GET(RESTAURANT_SEARCH)
    @Xml
    Observable<RestaurantList> getRestaurantSearch(@Query("serviceKey") String serviceKey, @Query("numOfRows") int numOfRows,
                                                   @Query("pageNo") int pageNo, @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp,
                                                   @Query("arrange") String arrange, @Query("listYN") String listYN, @Query("contentTypeId") int contentType, @Query("keyword") String keyword);

}
