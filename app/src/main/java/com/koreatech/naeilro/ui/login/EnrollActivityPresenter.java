package com.koreatech.naeilro.ui.login;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.network.entity.enroll.EnrollObject;
import com.koreatech.naeilro.network.interactor.EnrollInteractor;

public class EnrollActivityPresenter {
    private EnrollInteractor enrollInteractor;
    private EnrollActivityContract.View enrollView;

    public EnrollActivityPresenter(EnrollInteractor enrollInteractor, EnrollActivityContract.View enrollView) {
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
        enrollInteractor.getAccept(enrollApiCallback, name, pw, email);
    }
}
