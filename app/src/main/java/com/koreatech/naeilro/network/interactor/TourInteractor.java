package com.koreatech.naeilro.network.interactor;

import com.koreatech.core.network.ApiCallback;

public interface TourInteractor {
    void getTourItems(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN);

    void getTourCategoryItems(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN, int areaCode, int sigunguCode);

    void getCommonItems(ApiCallback apiCallback, int contentId, String mobileOS, String MobileApp);

    void getDetailInfoItems(ApiCallback apiCallback, int contentId, String mobileOS, String MobileApp);

    void getImageItems(ApiCallback apiCallback, int contentId, String mobileOS, String MobileApp);

    void getDetailIntroduceItems(ApiCallback apiCallback, int contentId, String mobileOS, String MobileApp);
}
