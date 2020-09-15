package com.koreatech.naeilro.network.service;

import com.koreatech.core.network.Xml;
import com.koreatech.naeilro.network.entity.search.SearchList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {
    String SEARCH = "rest/KorService/searchKeyword";

    @GET(SEARCH)
    @Xml
    Observable<SearchList> getSearchByKeyword(@Query("serviceKey") String serviceKey, @Query("numOfRows") int numOfRows,
                                              @Query("pageNo") int pageNo, @Query("MobileOS") String mobileOS, @Query("MobileApp") String mobileApp,
                                              @Query("arrange") String arrange, @Query("listYN") String listYN, @Query("contentTypeId") int contentType, @Query("keyword") String keyword);
}
