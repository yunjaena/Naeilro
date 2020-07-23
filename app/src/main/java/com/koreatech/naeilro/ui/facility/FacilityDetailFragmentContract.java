package com.koreatech.naeilro.ui.facility;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.facility.Facility;
import com.koreatech.naeilro.network.entity.reports.Reports;
import com.koreatech.naeilro.ui.facility.presenter.FacilityDetailFragmentPresenter;
import com.koreatech.naeilro.ui.reports.presenter.ReportsDetailFragmentPresenter;

import java.util.List;

public interface FacilityDetailFragmentContract {
    interface View extends BaseView<FacilityDetailFragmentPresenter> {
        void showDetailInfoList(List<Facility> facilityList);
        void showImageInfoList(List<Facility> facilityList);
        void showCommonInfo(Facility facility);
        void showLoading();
        void hideLoading();
    }
}
