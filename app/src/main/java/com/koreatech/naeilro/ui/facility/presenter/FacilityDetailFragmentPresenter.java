package com.koreatech.naeilro.ui.facility.presenter;

import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.facility.Facility;
import com.koreatech.naeilro.network.entity.reports.Reports;
import com.koreatech.naeilro.network.interactor.FacilityInteractor;
import com.koreatech.naeilro.network.interactor.ReportsInteractor;
import com.koreatech.naeilro.ui.facility.FacilityDetailFragmentContract;
import com.koreatech.naeilro.ui.reports.ReportsDetailFragmentContract;

import java.util.List;

public class FacilityDetailFragmentPresenter {
    private FacilityInteractor facilityInteractor;
    private FacilityDetailFragmentContract.View facilityDetailView;

    public FacilityDetailFragmentPresenter(FacilityInteractor facilityInteractor, FacilityDetailFragmentContract.View facilityDetailView) {
        this.facilityInteractor = facilityInteractor;
        this.facilityDetailView = facilityDetailView;
        facilityDetailView.setPresenter(this);
    }
    final ApiCallback facilityCommonApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            Facility facility = (Facility) object;
            facilityDetailView.showCommonInfo(facility);
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    };
    final ApiCallback facilityDetailInfoApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<Facility> facilityItems = (List<Facility>) object;
            facilityDetailView.showDetailInfoList(facilityItems);
        }

        @Override
        public void onFailure(Throwable throwable) {
            facilityDetailView.hideLoading();
        }
    };
    final ApiCallback facilityImageInfoApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<Facility> facilityItems = (List<Facility>) object;
            facilityDetailView.showImageInfoList(facilityItems);
            facilityDetailView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            facilityDetailView.hideLoading();
        }
    };

    public void getCommonInfo(int contentId){
        facilityDetailView.showLoading();
        facilityInteractor.getCommonItems(facilityCommonApiCallback, contentId, "AND", "nailro");
    }
    public void getDetailInfo(int contentId){
        facilityInteractor.getDetailItems(facilityDetailInfoApiCallback, contentId, "AND", "nailro");
    }
    public void getImageInfo(int contentId){
        facilityInteractor.getImageItems(facilityImageInfoApiCallback,contentId,"AND","nailro");
    }


}
