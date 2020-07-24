package com.koreatech.naeilro.ui.tourspot.presenter;

import androidx.annotation.StringRes;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.tour.TourInfo;

import java.util.List;

public interface TourSpotDetailContract {
    interface View extends BaseView<TourSpotDetailPresenter> {
        void showLoading();

        void hideLoading();

        void showMessage(String message);

        void showMessage(@StringRes int message);

        void showDetailIntroduceList(List<TourInfo> tourItems);

        void showDetailInfoList(List<TourInfo> tourItems);

        void showImageInfoList(List<TourInfo> tourItems);

        void showCommonInfo(TourInfo tour);
    }
}
