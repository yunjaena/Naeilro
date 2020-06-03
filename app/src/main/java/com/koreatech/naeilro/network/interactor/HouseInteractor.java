package com.koreatech.naeilro.network.interactor;

import com.koreatech.core.network.ApiCallback;

public interface HouseInteractor {
    void getHouseTotalCount(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN);

    void getHouseItems(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN);

    void getHouseCategoryItems(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN, int areaCode, int sigunguCode);
}
