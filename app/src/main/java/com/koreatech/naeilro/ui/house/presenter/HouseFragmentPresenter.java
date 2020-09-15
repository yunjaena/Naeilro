package com.koreatech.naeilro.ui.house.presenter;

import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.house.HouseInfo;
import com.koreatech.naeilro.network.interactor.HouseInteractor;
import com.koreatech.naeilro.ui.house.HouseInfoFragmentContract;

import java.util.List;

public class HouseFragmentPresenter {
    public static final String TAG = "HousePresenter";
    private HouseInteractor houseInteractor;
    private HouseInfoFragmentContract.View houseView;

    public HouseFragmentPresenter(HouseInteractor houseInteractor, HouseInfoFragmentContract.View houseView) {
        this.houseInteractor = houseInteractor;
        this.houseView = houseView;
        houseView.setPresenter(this);
    }



    final ApiCallback houseItemsApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<HouseInfo> houseItems = (List<HouseInfo>) object;
            houseView.showHouseList(houseItems);
            houseView.hideLoading();

        }

        @Override
        public void onFailure(Throwable throwable) {
            Log.e("fail", throwable.getMessage());
        }
    };
    final ApiCallback houseCategoryItemsApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<HouseInfo> houseItems = (List<HouseInfo>) object;
            houseView.showHouseList(houseItems);
            houseView.hideLoading();

        }

        @Override
        public void onFailure(Throwable throwable) {
            Log.e("fail", throwable.getMessage());
        }
    };



    public void getHouseItems(int pageNo) {
        houseView.showLoading();
        houseInteractor.getHouseItems(houseItemsApiCallback, 20, pageNo, "nailro", "A", "Y");
    }

    public void getHouseCategoryItems(int pageNo, int areaCode, int sigunguCode) {
        houseView.showLoading();
        houseInteractor.getHouseCategoryItems(houseItemsApiCallback, 20, pageNo, "nailro", "A", "Y", areaCode, sigunguCode);
    }
}
