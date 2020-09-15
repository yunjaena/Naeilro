package com.koreatech.naeilro.network.service;

import com.koreatech.core.network.Xml;
import com.koreatech.naeilro.network.entity.house.HouseInfoList;
import com.koreatech.naeilro.network.entity.reports.ReportsInfoList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReportsService {
    String REPORTS_LIST_INFO = "rest/KorService/areaBasedList";
    String REPORTS_COMMON_INFO = "rest/KorService/detailCommon";
    String REPORTS_DETAIL_INFO = "rest/KorService/detailInfo";
    String REPORTS_DETAILIMAGE_INFO = "rest/KorService/detailImage";
    @GET(REPORTS_LIST_INFO)
    @Xml
    Observable<ReportsInfoList> getReportsList(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId,@Query("listYN") String listYN, @Query("numOfRows") int numOfRows,
                                             @Query("pageNo") int pageNo, @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp
                                             );
    @GET(REPORTS_LIST_INFO)
    @Xml
    Observable<ReportsInfoList> getReportsCategoryList(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId,  @Query("areaCode") int areaCode,
                                                   @Query("sigunguCode") int sigunguCode, @Query("listYN") String listYN, @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp,
                                                   @Query("arrange") String arrange, @Query("numOfRows") int numOfRows,
                                                   @Query("pageNo") int pageNo);

    @GET(REPORTS_COMMON_INFO)
    @Xml
    Observable<ReportsInfoList> getReportsCommonInfo(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId, @Query("contentId") int contentId
                                                        , @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp,
                                                     @Query("firstImageYN") String firstImageYN, @Query("areacodeYN") String areacodeYN, @Query("addrinfoYN") String addrinfoYN,
                                                     @Query("mapinfoYN") String mapinfoYN, @Query("overviewYN") String overviewYN);
    @GET(REPORTS_DETAIL_INFO)
    @Xml
    Observable<ReportsInfoList> getReportsDetailnfo(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId, @Query("contentId") int contentId
                                                    , @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp);
    @GET(REPORTS_DETAILIMAGE_INFO)
    @Xml
    Observable<ReportsInfoList> getReportsImageInfo(@Query("serviceKey") String serviceKey, @Query("contentTypeId") int contentTypeId, @Query("contentId") int contentId
            , @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp);
}
