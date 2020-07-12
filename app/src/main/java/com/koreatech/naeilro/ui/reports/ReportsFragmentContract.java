package com.koreatech.naeilro.ui.reports;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.reports.Reports;
import com.koreatech.naeilro.ui.reports.presenter.ReportsFragmentPresenter;

import java.util.List;

public interface ReportsFragmentContract {
    interface View extends BaseView<ReportsFragmentPresenter>{
        void showReportsList(List<Reports> reportsList);
        void showLoading();
        void hideLoading();
    }
}
