package com.koreatech.naeilro.ui.tourspot.presenter;

import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.tour.TourInfo;
import com.koreatech.naeilro.network.entity.tour.TourInfo;
import com.koreatech.naeilro.network.interactor.TourInteractor;

import java.util.List;

public class TourSpotDetailPresenter {
    public static final String TAG = "TourSpotDetailPresenter";
    private TourInteractor tourInteractor;
    private TourSpotDetailContract.View tourDetailView;

    public TourSpotDetailPresenter(TourSpotDetailContract.View tourDetailView, TourInteractor tourInteractor) {
        this.tourInteractor = tourInteractor;
        this.tourDetailView = tourDetailView;
        tourDetailView.setPresenter(this);
    }

    final ApiCallback tourCommonApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            TourInfo tour = (TourInfo) object;
            tourDetailView.showCommonInfo(tour);
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    };
    final ApiCallback tourDetailInfoApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<TourInfo> tourItems = (List<TourInfo>) object;
            tourDetailView.showDetailInfoList(tourItems);
        }

        @Override
        public void onFailure(Throwable throwable) {
            Log.e(TAG, throwable.getMessage());
            tourDetailView.hideLoading();
        }
    };

    final ApiCallback tourDetailIntroduceApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<TourInfo> tourItems = (List<TourInfo>) object;
            tourDetailView.showDetailIntroduceList(tourItems);
        }

        @Override
        public void onFailure(Throwable throwable) {
            Log.e(TAG, throwable.getMessage());
            tourDetailView.hideLoading();
        }
    };
    
    
    final ApiCallback tourImageInfoApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<TourInfo> tourItems = (List<TourInfo>) object;
            tourDetailView.showImageInfoList(tourItems);
            tourDetailView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            tourDetailView.hideLoading();
        }
    };

    public void getTourCommonInfo(int contentId){
        tourDetailView.showLoading();
        tourInteractor.getCommonItems(tourCommonApiCallback, contentId, "AND", "nailro");
    }

    public void getTourDetailInfo(int contentId){
        tourDetailView.showLoading();
        tourInteractor.getDetailInfoItems(tourDetailInfoApiCallback, contentId, "AND", "nailro");
    }

    public void getTourDetailIntroduce(int contentId){
        tourDetailView.showLoading();
        tourInteractor.getDetailIntroduceItems(tourDetailIntroduceApiCallback, contentId, "AND", "nailro");
    }


    public void getTourImageInfo(int contentId){
        tourDetailView.showLoading();
        tourInteractor.getImageItems(tourImageInfoApiCallback, contentId, "AND", "nailro");
    }

}
