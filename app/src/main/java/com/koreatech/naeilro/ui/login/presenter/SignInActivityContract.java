package com.koreatech.naeilro.ui.login.presenter;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.user.EnrollObject;

public interface SignInActivityContract {
    interface View extends BaseView<SignInPresenter> {
        void showEnrollResult(EnrollObject enrollObject);
        void showLoading();
        void hideLoading();
        void showFailure();
    }
}
