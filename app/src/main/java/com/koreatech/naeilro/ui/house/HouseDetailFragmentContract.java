package com.koreatech.naeilro.ui.house;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.house.HouseInfo;
import com.koreatech.naeilro.ui.house.presenter.HouseDetailFragmentPresenter;

import java.util.List;

public interface HouseDetailFragmentContract {
    interface View extends BaseView<HouseDetailFragmentPresenter>{
        void showLoading();
        void hideLoading();
        void showDetailInfoList(List<HouseInfo> houseList);
        void showCommonInfo(HouseInfo houseInfo);
        void showHouseIntroInfo(HouseInfo houseInfo);
        void showImageInfoList(List<HouseInfo> houseList);
    }
}
