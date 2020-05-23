package com.koreatech.naeilro.ui.train.presenter;

import androidx.annotation.StringRes;

import com.koreatech.core.contract.BaseView;
import com.koreatech.naeilro.network.entity.traininfo.TrainInfo;

import java.util.List;

public interface TrainInfoFragmentContract {
    interface View extends BaseView<TrainInfoFragmentPresenter> {
        void showLoading();

        void hideLoading();

        void showMessage(String message);

        void showMessage(@StringRes int message);

        void showTrainList(List<TrainInfo> trainInfo);
    }
}
