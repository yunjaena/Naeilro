package com.koreatech.naeilro.ui.tourspot.presenter;

import androidx.annotation.StringRes;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.tour.TourInfo;

import java.util.List;

public interface TourSpotContract {
    interface View extends BaseView<TourSpotPresenter> {
        void showLoading();

        void hideLoading();

        void showMessage(String message);

        void showMessage(@StringRes int message);

        void showTourInfoList(List<TourInfo> tourInfoList);
    }
}
