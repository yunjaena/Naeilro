package com.koreatech.naeilro.ui.myplan.presenter;

import androidx.annotation.StringRes;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.myplan.RecommendPath;

import java.util.List;

public interface MyPlanRecommendPathContract {
    interface View extends BaseView<MyPlanRecommendPathPresenter> {
        void showMessage(String message);

        void showMessage(@StringRes int message);

        void showLoading();

        void hideLoading();

        void showRecommendPath(List<RecommendPath> recommendPathList);

        void failGetRecommendPath();
    }
}
