package com.koreatech.naeilro.network.interactor;

import com.koreatech.core.network.ApiCallback;

public interface FacilityInteractor {
    void getFacilityImtes(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN);
    void getFacilityCategoryItems(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN, int areaCode, int sigunguCode);
    void getCommonItems(ApiCallback apiCallback, int contentId, String MobileOs, String MobileApp);
    void getImageItems(ApiCallback apiCallback, int contentId, String MobileOs, String MobileApp);
    void getDetailItems(ApiCallback apiCallback, int contentId, String MobileOs, String MobileApp);
}
