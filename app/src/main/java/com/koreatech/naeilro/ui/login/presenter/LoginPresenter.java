package com.koreatech.naeilro.ui.login.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.interactor.UserInteractor;

public class LoginPresenter {
    private LoginContract.View loginView;
    final ApiCallback loginApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            loginView.successLogin();
            loginView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            loginView.hideLoading();
        }
    };
    private UserInteractor userInteractor;

    public LoginPresenter(LoginContract.View loginView, UserInteractor userInteractor) {
        this.loginView = loginView;
        this.userInteractor = userInteractor;
        loginView.setPresenter(this);
    }

    public void login(String email, String password) {
        loginView.showLoading();
        userInteractor.signIn(loginApiCallback, email, password);
    }
}
