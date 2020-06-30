package com.koreatech.naeilro.network.interactor;

import com.koreatech.core.network.ApiCallback;

public interface FestivalInteractor {
    void getFestivalImtes(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN);
    void getFestivalCategoryItems(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN, int areaCode, int sigunguCode);
}
