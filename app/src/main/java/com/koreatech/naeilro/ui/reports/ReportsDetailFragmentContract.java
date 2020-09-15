package com.koreatech.naeilro.ui.reports;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.reports.Reports;
import com.koreatech.naeilro.ui.reports.presenter.ReportsDetailFragmentPresenter;

import java.util.List;

public interface ReportsDetailFragmentContract {
    interface View extends BaseView<ReportsDetailFragmentPresenter> {
        void showDetailInfoList(List<Reports> reportsList);
        void showImageInfoList(List<Reports> reportsList);
        void showCommonInfo(Reports reports);
        void showLoading();
        void hideLoading();
    }
}
