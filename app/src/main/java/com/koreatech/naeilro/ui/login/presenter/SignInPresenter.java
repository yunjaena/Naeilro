package com.koreatech.naeilro.ui.login.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.user.EnrollObject;
import com.koreatech.naeilro.network.interactor.UserInteractor;

public class SignInPresenter {
    private UserInteractor enrollInteractor;
    private SignInActivityContract.View enrollView;

    public SignInPresenter(UserInteractor enrollInteractor, SignInActivityContract.View enrollView) {
        this.enrollInteractor = enrollInteractor;
        this.enrollView = enrollView;
        enrollView.setPresenter(this);
    }

    final ApiCallback enrollApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            EnrollObject enrollObject = (EnrollObject) object;
            enrollView.showEnrollResult(enrollObject);
            enrollView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            enrollView.showFailure();
        }
    };

    public void getSignInResult(String name, String email, String pw, String phoneNumber) {
        enrollView.showLoading();
        enrollInteractor.signUp(enrollApiCallback, name, pw, email, phoneNumber);
    }
}
