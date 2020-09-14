package com.koreatech.naeilro.ui.myinfo.presenter;

import androidx.annotation.StringRes;

import com.koreatech.core.contract.BaseView;

public interface ChangePasswordContract {
    interface View extends BaseView<ChangePasswordPresenter> {
        void showLoading();

        void hideLoading();

        void showMessage(String message);

        void showMessage(@StringRes int message);

        void showChangePasswordSuccess();

    }
}
