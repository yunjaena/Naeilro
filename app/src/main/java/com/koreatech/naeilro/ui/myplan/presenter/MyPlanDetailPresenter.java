package com.koreatech.naeilro.ui.myplan.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.DefaultMessage;
import com.koreatech.naeilro.network.entity.myplan.MyPlanNode;
import com.koreatech.naeilro.network.interactor.MyPlanInteractor;
import com.koreatech.naeilro.ui.myplan.MyPlanDetailContract;

import java.util.List;

public class MyPlanDetailPresenter {
    public static final String TAG = "MyPlanPresenter";
    private MyPlanDetailContract.View myPlanDetailView;
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
    final ApiCallback deleteNodeApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            DefaultMessage message = (DefaultMessage) object;
            myPlanDetailView.showMessage(message.getMessage());
        }

        @Override
        public void onFailure(Throwable throwable) {
            myPlanDetailView.failDeleteNode();
        }
    };
    private MyPlanInteractor myPlanInteractor;
    public MyPlanDetailPresenter(MyPlanDetailContract.View myPlanDetailView, MyPlanInteractor myPlanInteractor) {
        this.myPlanDetailView = myPlanDetailView;
        this.myPlanInteractor = myPlanInteractor;
        myPlanDetailView.setPresenter(this);
    }

    public void getMyPlanNodeList(String planNumber) {
        myPlanDetailView.showLoading();
        myPlanInteractor.getNode(getMyPlanNodeApiCallback, planNumber);
    }

    public void deleteNode(String nodeNumber, String contentType, String contentID) {
        myPlanDetailView.showLoading();
        myPlanInteractor.deleteNode(deleteNodeApiCallback, nodeNumber, contentType, contentID);
    }

}
