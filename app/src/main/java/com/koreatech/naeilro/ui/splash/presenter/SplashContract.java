package com.koreatech.naeilro.ui.splash.presenter;

import androidx.annotation.StringRes;

import com.koreatech.core.contract.BaseView;

public interface SplashContract {
    interface View extends BaseView<SplashPresenter> {
        void showMessage(String message);

        void showMessage(@StringRes int message);

        void goToLogin();

        void goToMain();
    }
}
