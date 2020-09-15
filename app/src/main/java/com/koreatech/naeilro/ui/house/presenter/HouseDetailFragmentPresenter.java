package com.koreatech.naeilro.ui.house.presenter;

import android.util.Log;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.house.HouseInfo;
import com.koreatech.naeilro.network.interactor.HouseInteractor;
import com.koreatech.naeilro.ui.house.HouseDetailFragmentContract;

import java.util.List;

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
            houseDetailView.showCommonInfo(houseInfo);
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
    final ApiCallback houseImageInfoApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<HouseInfo> houseItems =(List<HouseInfo>) object;
            houseDetailView.showImageInfoList(houseItems);
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    };

    public void getCommonInfo(int contentId){
        houseInteractor.getHouseCommonInfo(houseCommonInfoApiCallback, 32, contentId, "nailro");
    }
    public void getIntroInfo( int contentId){
        houseInteractor.getHouseIntroInfo(houseIntroInfoApiCallback, 32, contentId, "nailro");

    }
    public void getImageInfo(int contentId){
        houseInteractor.getImageItems(houseImageInfoApiCallback, contentId, "AND", "nailro");
    }
}
