package com.koreatech.naeilro.network.interactor;

import com.google.android.gms.common.api.Api;
import com.koreatech.core.network.ApiCallback;

public interface ReportsInteractor {
    void getReportsImtes(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN);
    void getRepostsCategoryItems(ApiCallback apiCallback, int numOfRows, int pageNo, String MobileApp, String arrange, String listYN, int areaCode, int sigunguCode);
    void getCommonItems(ApiCallback apiCallback, int contentId, String MobileOs, String MobileApp);
    void getImageItems(ApiCallback apiCallback, int contentId, String MobileOs, String MobileApp);
    void getDetailItems(ApiCallback apiCallback, int contentId, String MobileOs, String MobileApp);

}
