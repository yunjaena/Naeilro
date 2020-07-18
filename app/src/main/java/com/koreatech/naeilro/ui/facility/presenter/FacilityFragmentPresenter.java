package com.koreatech.naeilro.ui.facility.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.facility.Facility;
import com.koreatech.naeilro.network.interactor.FacilityInteractor;
import com.koreatech.naeilro.ui.facility.FacilityFragment;
import com.koreatech.naeilro.ui.facility.FacilityFragmentContract;

import java.util.List;

public class FacilityFragmentPresenter {
    private FacilityInteractor facilityInteractor;
    private FacilityFragmentContract.View facilityView;

    public FacilityFragmentPresenter(FacilityInteractor facilityInteractor, FacilityFragmentContract.View facilityView) {
        this.facilityInteractor = facilityInteractor;
        this.facilityView = facilityView;
        facilityView.setPresenter(this);
    }
    final ApiCallback facilityItemApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<Facility> facilityItems = (List<Facility>) object;
            facilityView.showFacilityList(facilityItems);
            facilityView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    };
    final ApiCallback facilityCategoryItemApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<Facility> facilityItems = (List<Facility>) object;
            facilityView.showFacilityList(facilityItems);
            facilityView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    };
    public void getFacilityItems(int pageNo){
        facilityView.showLoading();
        facilityInteractor.getFacilityImtes(facilityItemApiCallback, 20, pageNo,"nailro", "A", "Y");
    }
    public void getFacilityCategoryItems(int pageNo, int areaCode, int sigunguCode){
        facilityView.showLoading();
        facilityInteractor.getFacilityCategoryItems(facilityCategoryItemApiCallback, 20, pageNo, "nailro", "A", "Y", areaCode, sigunguCode);
    }
}
