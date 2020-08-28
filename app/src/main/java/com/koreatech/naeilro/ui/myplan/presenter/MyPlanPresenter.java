package com.koreatech.naeilro.ui.myplan.presenter;

import android.util.Log;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.myplan.MyPlan;
import com.koreatech.naeilro.network.interactor.MyPlanInteractor;
import com.koreatech.naeilro.ui.myplan.MyPlanContract;

import java.util.List;

public class MyPlanPresenter {
    public static final String TAG = "MyPlanPresenter";
    private MyPlanContract.View myPlanView;
    private MyPlanInteractor myPlanInteractor;

    public MyPlanPresenter(MyPlanContract.View myPlanView, MyPlanInteractor myPlanInteractor) {
        this.myPlanView = myPlanView;
        this.myPlanInteractor = myPlanInteractor;
        myPlanView.setPresenter(this);
    }
    final ApiCallback getMyPlanApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<MyPlan> myPlanList = (List<MyPlan>) object;
            myPlanView.showMyPlanCollection(myPlanList);
            myPlanView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            myPlanView.failGetPlanInfo();
        }
    };
    public void getMyPlanCollectionList() {
        myPlanView.showLoading();
        myPlanInteractor.getPlan(getMyPlanApiCallback);
    }

}
