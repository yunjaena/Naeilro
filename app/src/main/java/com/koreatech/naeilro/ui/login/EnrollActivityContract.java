package com.koreatech.naeilro.ui.login;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.enroll.EnrollObject;

public interface EnrollActivityContract {
    interface View extends BaseView<EnrollActivityPresenter> {
        void showEnrollResult(EnrollObject enrollObject);
        void showLoading();
        void hideLoading();
    }
}
