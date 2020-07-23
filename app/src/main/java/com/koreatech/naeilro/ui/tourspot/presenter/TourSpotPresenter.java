package com.koreatech.naeilro.ui.tourspot.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.tour.TourInfo;
import com.koreatech.naeilro.network.interactor.TourInteractor;

import java.util.List;

public class TourSpotPresenter {
    private TourInteractor tourInteractor;
    private TourSpotContract.View tourView;
    final ApiCallback tourItemsApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<TourInfo> tourInfoList = (List<TourInfo>) object;
            tourView.showTourInfoList(tourInfoList);
            tourView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            tourView.hideLoading();
        }
    };

    public TourSpotPresenter(TourSpotContract.View tourView, TourInteractor tourInteractor) {
        this.tourInteractor = tourInteractor;
        this.tourView = tourView;
        tourView.setPresenter(this);
    }

    public void getTourInfoList(int pageNumber, int numOfRows) {
        tourView.showLoading();
        tourInteractor.getTourItems(tourItemsApiCallback, numOfRows, pageNumber, "nailro", "A", "Y");
    }

    public void getTourInfoList(int pageNumber, int numOfRows, int areaCode, int sigunguCode) {
        tourView.showLoading();
        tourInteractor.getTourCategoryItems(tourItemsApiCallback, numOfRows, pageNumber, "nailro", "A", "Y", areaCode, sigunguCode);
    }

}
