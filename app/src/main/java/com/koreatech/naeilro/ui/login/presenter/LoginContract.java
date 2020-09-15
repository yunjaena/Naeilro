package com.koreatech.naeilro.ui.login.presenter;

import androidx.annotation.StringRes;

import com.koreatech.core.contract.BaseView;

public interface LoginContract {
    interface View extends BaseView<LoginPresenter> {
        void showLoading();

        void hideLoading();

        void showMessage(String message);

        void showMessage(@StringRes int message);

        void successLogin();
    }
}
