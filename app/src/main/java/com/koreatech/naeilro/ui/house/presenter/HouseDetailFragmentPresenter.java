package com.koreatech.naeilro.ui.house.presenter;

import android.util.Log;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.house.HouseInfo;
import com.koreatech.naeilro.network.interactor.HouseInteractor;
import com.koreatech.naeilro.ui.house.HouseDetailFragmentContract;

public class HouseDetailFragmentPresenter {
    public static final String TAG = "HouseDetailPresenter";
    private HouseInteractor houseInteractor;
    private HouseDetailFragmentContract.View houseDetailView;

    public HouseDetailFragmentPresenter(HouseInteractor houseInteractor, HouseDetailFragmentContract.View houseDetailView) {
        this.houseInteractor = houseInteractor;
        this.houseDetailView = houseDetailView;
        houseDetailView.setPresenter(this);
    }
    final ApiCallback houseCommonInfoApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            HouseInfo houseInfo = (HouseInfo) object;
            houseDetailView.showHouseCommonInfo(houseInfo);
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    };
    final ApiCallback houseIntroInfoApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            HouseInfo houseInfo = (HouseInfo) object;
            houseDetailView.showHouseIntroInfo(houseInfo);
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    };

    public void getHouseCommonInfo(int contentTypeId, int contentId){
        houseInteractor.getHouseCommonInfo(houseCommonInfoApiCallback, contentTypeId, contentId, "nailro");
    }
    public void getHouseIntroInfo(int contentTypeId, int contentId){
        Log.e("presenter", "getHouseIntroInfo");
        houseInteractor.getHouseIntroInfo(houseIntroInfoApiCallback, contentTypeId, contentId, "nailro");
    }
}
