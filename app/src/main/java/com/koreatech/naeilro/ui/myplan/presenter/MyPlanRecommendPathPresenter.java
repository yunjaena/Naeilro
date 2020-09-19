package com.koreatech.naeilro.ui.myplan.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.myplan.RecommendPath;
import com.koreatech.naeilro.network.interactor.MyPlanInteractor;

import java.util.List;

public class MyPlanRecommendPathPresenter {
    private MyPlanRecommendPathContract.View recommendPathView;
    final ApiCallback recommendPathApiCallBack = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            if (object != null)
                recommendPathView.showRecommendPath((List<RecommendPath>) object);
            recommendPathView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            recommendPathView.showMessage(throwable.getMessage());
            recommendPathView.hideLoading();
        }
    };
    private MyPlanInteractor myPlanInteractor;

    public MyPlanRecommendPathPresenter(MyPlanRecommendPathContract.View recommendPathView, MyPlanInteractor myPlanInteractor) {
        this.recommendPathView = recommendPathView;
        this.myPlanInteractor = myPlanInteractor;
    }

    public void getRecommendPath(String planNumber, String departNodeNumber, String arrivalNodeNumber) {
        recommendPathView.showLoading();
        myPlanInteractor.getRecommendPath(recommendPathApiCallBack, planNumber, departNodeNumber, arrivalNodeNumber);
    }


}
