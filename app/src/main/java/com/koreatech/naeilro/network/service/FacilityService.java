package com.koreatech.naeilro.network.service;

import com.koreatech.core.network.Xml;
import com.koreatech.naeilro.network.entity.facility.FacilityInfoList;
import com.koreatech.naeilro.network.entity.reports.ReportsInfoList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FacilityService {
    String FACILITY_LIST_INFO = "rest/KorService/areaBasedList";
    String FACILITY_COMMON_INFO = "rest/KorService/detailCommon";
    String FACILITY_DETAIL_INFO = "rest/KorService/detailInfo";
    String FACILITY_DETAILIMAGE_INFO = "rest/KorService/detailImage";

    @GET(FACILITY_LIST_INFO)
    @Xml
    Observable<FacilityInfoList> getFacilityList(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId, @Query("listYN") String listYN, @Query("numOfRows") int numOfRows,
                                                @Query("pageNo") int pageNo, @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp
    );

    @GET(FACILITY_LIST_INFO)
    @Xml
    Observable<FacilityInfoList> getFacilityCategoryList(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId,  @Query("areaCode") int areaCode,
                                                       @Query("sigunguCode") int sigunguCode, @Query("listYN") String listYN, @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp,
                                                       @Query("arrange") String arrange, @Query("numOfRows") int numOfRows,
                                                       @Query("pageNo") int pageNo);

    @GET(FACILITY_COMMON_INFO)
    @Xml
    Observable<FacilityInfoList> getFacilityCommonInfo(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId, @Query("contentId") int contentId
            , @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp,
                                                     @Query("firstImageYN") String firstImageYN, @Query("areacodeYN") String areacodeYN, @Query("addrinfoYN") String addrinfoYN,
                                                     @Query("mapinfoYN") String mapinfoYN, @Query("overviewYN") String overviewYN);
    @GET(FACILITY_DETAIL_INFO)
    @Xml
    Observable<FacilityInfoList> getFacilityDetailnfo(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId, @Query("contentId") int contentId
            , @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp);
    @GET(FACILITY_DETAILIMAGE_INFO)
    @Xml
    Observable<FacilityInfoList> getFacilityImageInfo(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId, @Query("contentId") int contentId
            , @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp);

}
