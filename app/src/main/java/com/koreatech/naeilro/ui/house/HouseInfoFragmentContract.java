package com.koreatech.naeilro.ui.house;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.house.HouseInfo;
import com.koreatech.naeilro.ui.house.presenter.HouseFragmentPresenter;

import java.util.List;

public interface HouseInfoFragmentContract {
    interface View extends BaseView<HouseFragmentPresenter> {
        void showHouseList(List<HouseInfo> houseList);
        void showLoading();
        void hideLoading();
    }
}
