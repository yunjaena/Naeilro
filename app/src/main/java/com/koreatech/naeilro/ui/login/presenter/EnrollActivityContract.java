package com.koreatech.naeilro.ui.login.presenter;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.user.EnrollObject;

public interface EnrollActivityContract {
    interface View extends BaseView<EnrollActivityPresenter> {
        void showEnrollResult(EnrollObject enrollObject);
        void showLoading();
        void hideLoading();
    }
}
