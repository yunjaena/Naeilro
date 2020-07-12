package com.koreatech.naeilro.ui.reports.presenter;

import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.reports.Reports;
import com.koreatech.naeilro.network.interactor.ReportsInteractor;
import com.koreatech.naeilro.ui.reports.ReportsFragmentContract;

import java.util.List;

public class ReportsFragmentPresenter {
    public static final String TAG = "ReportsPresenter";
    private ReportsInteractor reportsInteractor;
    private ReportsFragmentContract.View reportsView;

    public ReportsFragmentPresenter(ReportsInteractor reportsInteractor, ReportsFragmentContract.View reportsView) {
        this.reportsInteractor = reportsInteractor;
        this.reportsView = reportsView;
        reportsView.setPresenter(this);
    }
    final ApiCallback reportsItemApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<Reports> reportsItems = (List<Reports>) object;
            reportsView.showReportsList(reportsItems);
            reportsView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    };
    final ApiCallback reportsCategoryItemApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<Reports> reportsItems = (List<Reports>) object;
            reportsView.showReportsList(reportsItems);
            reportsView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    };
    public void getReportsItems(int pageNo){
        reportsView.showLoading();;
        reportsInteractor.getReportsImtes(reportsItemApiCallback, 20, pageNo, "nailro","A", "Y");
    }
    public void getReportsCategoryItems(int pageNo, int areaCode, int sigunguCode){
        reportsView.showLoading();
        reportsInteractor.getRepostsCategoryItems(reportsCategoryItemApiCallback, 20, pageNo, "nailro","A","Y", areaCode, sigunguCode);

    }
}
