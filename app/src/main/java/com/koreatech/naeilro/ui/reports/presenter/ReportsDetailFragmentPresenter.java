package com.koreatech.naeilro.ui.reports.presenter;

import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.reports.Reports;
import com.koreatech.naeilro.network.interactor.ReportsInteractor;
import com.koreatech.naeilro.ui.reports.ReportsDetailFragmentContract;

import java.util.List;

public class ReportsDetailFragmentPresenter {
    public static final String TAG = "ReportsDetailPresenter";
    private ReportsInteractor reportsInteractor;
    private ReportsDetailFragmentContract.View reportsDetailView;

    public ReportsDetailFragmentPresenter(ReportsInteractor reportsInteractor, ReportsDetailFragmentContract.View reportsDetailView) {
        this.reportsInteractor = reportsInteractor;
        this.reportsDetailView = reportsDetailView;
        reportsDetailView.setPresenter(this);
    }
    final ApiCallback reportsCommonApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            Reports reports = (Reports) object;
            reportsDetailView.showCommonInfo(reports);
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    };
    final ApiCallback reportsDetailInfoApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<Reports> reportsItems = (List<Reports>) object;
            reportsDetailView.showDetailInfoList(reportsItems);
        }

        @Override
        public void onFailure(Throwable throwable) {
            Log.e(TAG, throwable.getMessage());
            reportsDetailView.hideLoading();
        }
    };
    final ApiCallback reportsImageInfoApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<Reports> reportsItems = (List<Reports>) object;
            reportsDetailView.showImageInfoList(reportsItems);
            reportsDetailView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            reportsDetailView.hideLoading();
        }
    };

    public void getComonInfo(int contentId){
        reportsDetailView.showLoading();
        reportsInteractor.getCommonItems(reportsCommonApiCallback, contentId, "AND", "nailro");
    }
    public void getDetailInfo(int contentId){
        reportsInteractor.getDetailItems(reportsDetailInfoApiCallback, contentId, "AND", "nailro");
    }
    public void getImageInfo(int contentId){
        reportsInteractor.getImageItems(reportsImageInfoApiCallback,contentId,"AND","nailro");
    }
}
