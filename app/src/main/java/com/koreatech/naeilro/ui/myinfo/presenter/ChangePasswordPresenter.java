package com.koreatech.naeilro.ui.myinfo.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.user.Token;
import com.koreatech.naeilro.network.interactor.UserInteractor;

public class ChangePasswordPresenter {
    final ApiCallback changePasswordApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            Token token = (Token) object;
            if(token.getSuccess() == 1){
                changePasswordView.showChangePasswordSuccess();
            }else{
                changePasswordView.showMessage(R.string.password_change_failed);
            }
            changePasswordView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            changePasswordView.hideLoading();
            changePasswordView.showMessage(throwable.getMessage());
        }
    };
    private ChangePasswordContract.View changePasswordView;
    private UserInteractor userInteractor;

    public ChangePasswordPresenter(ChangePasswordContract.View changePasswordView, UserInteractor userInteractor) {
        this.changePasswordView = changePasswordView;
        this.userInteractor = userInteractor;
    }

    public void changePassword(String email, String password) {
        changePasswordView.showLoading();
        userInteractor.changePassword(changePasswordApiCallback, email, password);
    }

}
