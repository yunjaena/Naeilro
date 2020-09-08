package com.koreatech.naeilro.ui.restraunt.presenter;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.facility.Facility;
import com.koreatech.naeilro.network.entity.restaurant.RestaurantInfo;
import com.koreatech.naeilro.ui.facility.presenter.FacilityDetailFragmentPresenter;

import java.util.List;

public interface RestaurantDetailContract {
    interface View extends BaseView<RestaurantDetailPresenter> {
        void showDetailInfoList(RestaurantInfo restaurantList);
        void showImageInfoList(List<RestaurantInfo> restaurantList);
        void showCommonInfo(RestaurantInfo restaurant);
        void showLoading();
        void hideLoading();
    }
}
