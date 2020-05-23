package com.koreatech.naeilro.ui.train.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.traincitycode.TrainCityList;
import com.koreatech.naeilro.network.entity.traininfo.TrainList;
import com.koreatech.naeilro.network.interactor.TrainInteractor;
import com.koreatech.naeilro.util.DataAPIMessageUtil;

public class TrainInfoFragmentPresenter {
    public static final String TAG = "TrainInfoPresenter";
    private TrainInfoFragmentContract.View trainInfoView;
    private TrainInteractor trainInteractor;

    public TrainInfoFragmentPresenter(TrainInfoFragmentContract.View trainInfoView, TrainInteractor trainInteractor) {
        this.trainInfoView = trainInfoView;
        this.trainInteractor = trainInteractor;
        trainInfoView.setPresenter(this);
    }

    final ApiCallback trainListApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            TrainList trainList = (TrainList) object;
            if (DataAPIMessageUtil.isSuccess(trainList.getMessageList().get(0)))
                trainInfoView.showTrainList(trainList.getTrainInfoItem().get(0).getTrainInfoItemList().get(0).getTrainInfoBodyList());
            else
                trainInfoView.showMessage(R.string.train_info_fail);
            trainInfoView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            trainInfoView.showMessage(R.string.train_info_fail);
            trainInfoView.hideLoading();
        }
    };

    final ApiCallback trainCityListApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            TrainCityList trainCityList = (TrainCityList) object;
            if (DataAPIMessageUtil.isSuccess(trainCityList.getMessageList().get(0)))
                trainInfoView.showTrainCityList(trainCityList.getTrainCityInfoBodyList().get(0).getTrainCityInfoItemList().get(0).getTrainCityInfoList());
            else
                trainInfoView.showMessage(R.string.train_city_info_fail);
            trainInfoView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            trainInfoView.showMessage(R.string.train_city_info_fail);
            trainInfoView.hideLoading();
        }
    };

    public void getTrainList() {
        trainInfoView.showLoading();
        trainInteractor.getTrainList(trainListApiCallback);
    }

    public void getTrainCityList(){
        trainInfoView.showLoading();
        trainInteractor.getTrainCityList(trainCityListApiCallback);
    }
}
