package com.koreatech.naeilro.ui.main;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.house.HouseInfo;
import com.koreatech.naeilro.network.entity.recommend.MyRecommendItem;
import com.koreatech.naeilro.ui.house.presenter.HouseFragmentPresenter;
import com.koreatech.naeilro.ui.main.presenter.HomePresenter;

import java.util.List;

public interface HomeFragmentContract {
    interface View extends BaseView<HomePresenter> {
        void showRecommendList(List<MyRecommendItem> recommendList);
        void showNullRecommendList(String message);
        void showLoading();
        void hideLoading();
        void showFailure(String s);
    }
}
