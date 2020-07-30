package com.koreatech.naeilro.ui.login.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.user.EnrollObject;
import com.koreatech.naeilro.network.interactor.UserInteractor;

public class EnrollActivityPresenter {
    private UserInteractor enrollInteractor;
    private EnrollActivityContract.View enrollView;

    public EnrollActivityPresenter(UserInteractor enrollInteractor, EnrollActivityContract.View enrollView) {
        this.enrollInteractor = enrollInteractor;
        this.enrollView = enrollView;
        enrollView.setPresenter(this);
    }
    final ApiCallback enrollApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            EnrollObject enrollObject = (EnrollObject) object;
            enrollView.showEnrollResult(enrollObject);
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    };
    public void getEnrollResult(String name, String email, String pw){
        enrollInteractor.signUp(enrollApiCallback, name, pw, email);
    }
}
