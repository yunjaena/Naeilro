package com.koreatech.naeilro.ui.myinfo.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.user.UserInfo;
import com.koreatech.naeilro.network.interactor.UserInteractor;
import com.koreatech.naeilro.ui.myinfo.MyInfoContract;
import com.koreatech.naeilro.ui.myinfo.MyInfoFragment;

public class MyInfoPresenter {
    private UserInteractor userInteractor;
    private MyInfoContract.View myInfoView;

    public MyInfoPresenter(UserInteractor userInteractor, MyInfoContract.View myInfoView) {
        this.userInteractor = userInteractor;
        this.myInfoView = myInfoView;
        myInfoView.setPresenter(this);
    }
    final ApiCallback userInfoApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            UserInfo userInfo = (UserInfo) object;
            myInfoView.showInfo(userInfo);
            myInfoView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            myInfoView.hideLoading();
        }
    };
    final ApiCallback logOutfoApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            UserInfo userInfo = (UserInfo) object;
            myInfoView.showLogOut(userInfo);
            myInfoView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            myInfoView.hideLoading();
        }
    };
    public void getUserInfo(String token){
        myInfoView.showLoading();
        userInteractor.getUserInfo(userInfoApiCallback, token);
    }
    public void logOut(String token){
        userInteractor.logOut(logOutfoApiCallback, token);
    }
}
