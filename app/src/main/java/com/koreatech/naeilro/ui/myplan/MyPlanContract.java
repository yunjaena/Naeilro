package com.koreatech.naeilro.ui.myplan;

import androidx.annotation.StringRes;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.myplan.MyPlan;
import com.koreatech.naeilro.network.entity.user.UserInfo;
import com.koreatech.naeilro.ui.myplan.presenter.MyPlanPresenter;

import java.util.List;

public interface MyPlanContract {
    interface View extends BaseView<MyPlanPresenter>{
        void showMessage(String message);

        void showMessage(@StringRes int message);

        void showLoading();

        void hideLoading();

        void showMyPlanCollection(List<MyPlan> myPlanList);

        void failGetPlanInfo();
    }
}
