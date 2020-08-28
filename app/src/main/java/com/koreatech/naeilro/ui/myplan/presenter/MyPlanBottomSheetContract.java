package com.koreatech.naeilro.ui.myplan.presenter;

import androidx.annotation.StringRes;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.myplan.MyPlan;

import java.util.List;

public interface MyPlanBottomSheetContract {
    interface View extends BaseView<MyPlanBottomSheetPresenter> {
        void showMessage(String message);

        void showMessage(@StringRes int message);

        void showLoading();

        void hideLoading();

        void showMyPlanCollection(List<MyPlan> myPlanList);

        void failGetPlanInfo();

        void showSuccessCreatePlan();
    }
}
