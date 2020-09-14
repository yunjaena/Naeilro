package com.koreatech.naeilro.ui.myinfo.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.user.UserInfo;
import com.koreatech.naeilro.network.interactor.UserInteractor;

public class MyInfoPresenter {
    private UserInteractor userInteractor;
    private MyInfoContract.View myInfoView;
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
    final ApiCallback logOutApiCallback = new ApiCallback() {
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
    final ApiCallback deActivateAccountApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            UserInfo userInfo = (UserInfo) object;
            myInfoView.showDeactivateAccount(userInfo);
            myInfoView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            myInfoView.hideLoading();
        }
    };

    public MyInfoPresenter(UserInteractor userInteractor, MyInfoContract.View myInfoView) {
        this.userInteractor = userInteractor;
        this.myInfoView = myInfoView;
        myInfoView.setPresenter(this);
    }

    public void getUserInfo(String token) {
        myInfoView.showLoading();
        userInteractor.getUserInfo(userInfoApiCallback, token);
    }

    public void logOut(String token) {
        userInteractor.logOut(logOutApiCallback, token);
    }

    public void deactivateAccount(String token) {
        userInteractor.deactivateAccount(deActivateAccountApiCallback, token);
    }
}
