package com.koreatech.naeilro.ui.myplan;

import androidx.annotation.StringRes;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.myplan.MyPlan;
import com.koreatech.naeilro.network.entity.myplan.MyPlanNode;
import com.koreatech.naeilro.ui.myplan.presenter.MyPlanDetailPresenter;
import com.koreatech.naeilro.ui.myplan.presenter.MyPlanPresenter;

import java.util.List;

public interface MyPlanDetailContract {
    interface View extends BaseView<MyPlanDetailPresenter> {
        void showMessage(String message);

        void showMessage(@StringRes int message);

        void showLoading();

        void hideLoading();

        void showMyPlanNode(List<MyPlanNode> myPlanList);

        void failGetPlanInfo();
        void failDeleteNode();
    }
}
