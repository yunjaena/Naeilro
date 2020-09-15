package com.koreatech.naeilro.ui.restraunt.presenter;

import androidx.annotation.StringRes;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.restaurant.RestaurantInfo;

import java.util.List;

public interface RestaurantContract {
    interface View extends BaseView<RestaurantPresenter> {
        void showLoading();

        void hideLoading();

        void showMessage(String message);

        void showMessage(@StringRes int message);

        void showRestaurantInfoList(List<RestaurantInfo> restaurantInfoList);
    }
}
