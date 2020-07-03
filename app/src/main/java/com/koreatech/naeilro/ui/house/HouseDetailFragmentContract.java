package com.koreatech.naeilro.ui.house;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.house.HouseInfo;
import com.koreatech.naeilro.ui.house.presenter.HouseDetailFragmentPresenter;

public interface HouseDetailFragmentContract {
    interface View extends BaseView<HouseDetailFragmentPresenter>{
        void showLoading();
        void hideLoading();
        void showHouseCommonInfo(HouseInfo hosueInfo);
        void showHouseIntroInfo(HouseInfo houseInfo);
    }
}
