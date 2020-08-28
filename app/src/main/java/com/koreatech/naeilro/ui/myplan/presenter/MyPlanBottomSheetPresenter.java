package com.koreatech.naeilro.ui.myplan.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.DefaultMessage;
import com.koreatech.naeilro.network.entity.myplan.MyPlan;
import com.koreatech.naeilro.network.entity.myplan.MyPlanNode;
import com.koreatech.naeilro.network.interactor.MyPlanInteractor;

import java.util.List;

public class MyPlanBottomSheetPresenter {
    public static final String TAG = "MyPlanBottomSheetPresenter";
    private MyPlanBottomSheetContract.View myPlanBottomSheetView;
    final ApiCallback getMyPlanApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<MyPlan> myPlanList = (List<MyPlan>) object;
            myPlanBottomSheetView.showMyPlanCollection(myPlanList);
            myPlanBottomSheetView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            myPlanBottomSheetView.failGetPlanInfo();
        }
    };

    final ApiCallback createPlanApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            DefaultMessage defaultMessage = (DefaultMessage) object;
            myPlanBottomSheetView.hideLoading();
            if(defaultMessage.isSuccess()){
                getMyPlanCollectionList();
            }
        }

        @Override
        public void onFailure(Throwable throwable) {
            myPlanBottomSheetView.hideLoading();
            myPlanBottomSheetView.failGetPlanInfo();
        }
    };
    private MyPlanInteractor myPlanInteractor;

    public MyPlanBottomSheetPresenter(MyPlanBottomSheetContract.View myPlanBottomSheetView, MyPlanInteractor myPlanInteractor) {
        this.myPlanBottomSheetView = myPlanBottomSheetView;
        this.myPlanInteractor = myPlanInteractor;
    }

    public void getMyPlanCollectionList() {
        myPlanBottomSheetView.showLoading();
        myPlanInteractor.getPlan(getMyPlanApiCallback);
    }

    public void getMyPlanCollectionList(MyPlanNode myPlanNode) {
        myPlanBottomSheetView.showLoading();
        myPlanInteractor.getPlanWithSelectNode(getMyPlanApiCallback, myPlanNode);
    }

    public void createPlan(String name){
        myPlanBottomSheetView.showLoading();
        myPlanInteractor.createPlan(createPlanApiCallback, name);
    }
}
