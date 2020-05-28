package com.koreatech.naeilro.ui.train.presenter;

import androidx.annotation.StringRes;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.traincitycode.TrainCityInfo;
import com.koreatech.naeilro.network.entity.traininfo.TrainInfo;
import com.koreatech.naeilro.network.entity.trainstaion.TrainStationInfo;
import com.koreatech.naeilro.network.entity.trainstaion.TrainStationList;

import java.util.List;

public interface TrainInfoFragmentContract {
    interface View extends BaseView<TrainInfoFragmentPresenter> {
        void showLoading();

        void hideLoading();

        void showMessage(String message);

        void showMessage(@StringRes int message);

        void showTrainList(List<TrainInfo> trainInfo);

        void showTrainCityList(List<TrainCityInfo> trainCityInfoList);

        void showTrainStationList(List<TrainStationInfo> trainStationInfoList);
    }
}
