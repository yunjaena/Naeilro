package com.koreatech.naeilro.ui.myinfo.presenter;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.user.UserInfo;
import com.koreatech.naeilro.ui.myinfo.presenter.MyInfoPresenter;

public interface MyInfoContract {
    interface View extends BaseView<MyInfoPresenter> {
        void showLoading();

        void hideLoading();

        void showInfo(UserInfo userInfo);

        void showLogOut(UserInfo userInfo);

        void showDeactivateAccount(UserInfo userInfo);
    }
}
