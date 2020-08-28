package com.koreatech.naeilro.ui.myplan.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.myplan.MyPlanNode;
import com.koreatech.naeilro.network.interactor.MyPlanInteractor;
import com.koreatech.naeilro.ui.myplan.MyPlanContract;
import com.koreatech.naeilro.ui.myplan.MyPlanDetailContract;

import java.util.List;

public class MyPlanDetailPresenter {
    public static final String TAG = "MyPlanPresenter";
    private MyPlanDetailContract.View myPlanDetailView;
    private MyPlanInteractor myPlanInteractor;

    public MyPlanDetailPresenter(MyPlanDetailContract.View myPlanDetailView, MyPlanInteractor myPlanInteractor) {
        this.myPlanDetailView = myPlanDetailView;
        this.myPlanInteractor = myPlanInteractor;
        myPlanDetailView.setPresenter(this);
    }
    final ApiCallback getMyPlanNodeApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<MyPlanNode> myPlanNodeList = (List<MyPlanNode>) object;
            myPlanDetailView.showMyPlanNode(myPlanNodeList);
            myPlanDetailView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            myPlanDetailView.failGetPlanInfo();
        }
    };
    public void getMyPlanNodeList(String planNumber){
        myPlanDetailView.showLoading();
        myPlanInteractor.getNode(getMyPlanNodeApiCallback, planNumber);
    }

}
