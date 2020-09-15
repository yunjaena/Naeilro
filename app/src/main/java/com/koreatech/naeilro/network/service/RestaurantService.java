package com.koreatech.naeilro.network.service;

import com.koreatech.core.network.Xml;
import com.koreatech.naeilro.network.entity.restaurant.RestaurantList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestaurantService {
    String RESTAURANT_LIST_INFO = "rest/KorService/areaBasedList";
    String RESTAURANT_COMMON_INFO = "rest/KorService/detailCommon";
    String RESTAURANT_DETAIL_INFO = "rest/KorService/detailIntro";
    String RESTAURANT_DETAILIMAGE_INFO = "rest/KorService/detailImage";

    @GET(RESTAURANT_LIST_INFO)
    @Xml
    Observable<RestaurantList> getRestaurantList(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId, @Query("listYN") String listYN, @Query("numOfRows") int numOfRows,
                                                 @Query("pageNo") int pageNo, @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp
    );

    @GET(RESTAURANT_LIST_INFO)
    @Xml
    Observable<RestaurantList> getRestaurantCategoryList(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId,  @Query("areaCode") int areaCode,
                                                         @Query("sigunguCode") int sigunguCode, @Query("listYN") String listYN, @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp,
                                                         @Query("arrange") String arrange, @Query("numOfRows") int numOfRows,
                                                         @Query("pageNo") int pageNo);

    @GET(RESTAURANT_COMMON_INFO)
    @Xml
    Observable<RestaurantList> getRestaurantCommonInfo(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId, @Query("contentId") int contentId
            , @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp,@Query("defaultYN") String defaultYN,
                                                       @Query("firstImageYN") String firstImageYN, @Query("areacodeYN") String areacodeYN, @Query("catcodeYN") String catcodeYN,@Query("addrinfoYN") String addrinfoYN,
                                                       @Query("mapinfoYN") String mapinfoYN, @Query("overviewYN") String overviewYN);
    @GET(RESTAURANT_DETAIL_INFO)
    @Xml
    Observable<RestaurantList> getRestaurantDetailnfo(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId, @Query("contentId") int contentId
            , @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp);
    @GET(RESTAURANT_DETAILIMAGE_INFO)
    @Xml
    Observable<RestaurantList> getRestaurantImageInfo(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId, @Query("contentId") int contentId
            , @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp);
}
