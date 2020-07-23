package com.koreatech.naeilro.ui.facility;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.facility.Facility;
import com.koreatech.naeilro.ui.facility.presenter.FacilityFragmentPresenter;
import com.koreatech.naeilro.ui.reports.presenter.ReportsFragmentPresenter;

import java.util.List;

public interface FacilityFragmentContract {
    interface View extends BaseView<FacilityFragmentPresenter> {
        void showFacilityList(List<Facility> facilityList);
        void showLoading();
        void hideLoading();
    }
}
