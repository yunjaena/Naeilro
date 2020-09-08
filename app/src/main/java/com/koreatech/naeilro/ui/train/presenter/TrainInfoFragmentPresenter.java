package com.koreatech.naeilro.ui.train.presenter;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.traincitycode.TrainCityList;
import com.koreatech.naeilro.network.entity.traininfo.TrainInfo;
import com.koreatech.naeilro.network.entity.traininfo.TrainList;
import com.koreatech.naeilro.network.entity.trainsearch.TrainSearchInfo;
import com.koreatech.naeilro.network.entity.trainstaion.TrainStationInfo;
import com.koreatech.naeilro.network.interactor.TrainInteractor;
import com.koreatech.naeilro.util.DataAPIMessageUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrainInfoFragmentPresenter {
    public static final String TAG = "TrainInfoPresenter";
    private TrainInfoFragmentContract.View trainInfoView;
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
    final ApiCallback trainStationListApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<TrainStationInfo> trainStationInfoList = (List<TrainStationInfo>) object;
            trainInfoView.showTrainStationList(trainStationInfoList);
            trainInfoView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            trainInfoView.showMessage(R.string.train_city_info_fail);
            trainInfoView.hideLoading();
        }
    };
    final ApiCallback trainSearchListApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            List<TrainSearchInfo> trainStationInfoList = (List<TrainSearchInfo>) object;
            if (trainStationInfoList.size() > 0)
                trainInfoView.showTrainSearchList(trainStationInfoList);
            else
                trainInfoView.showMessage(R.string.train_info_empty);
            trainInfoView.hideLoading();
        }

        @Override
        public void onFailure(Throwable throwable) {
            trainInfoView.showMessage(R.string.train_info_fail);
            trainInfoView.hideLoading();
        }
    };
    private TrainInteractor trainInteractor;
    private Set<String> trainFilterSet = new HashSet<String>(Arrays.asList("00", "07", "08", "17", "02", "01", "04", "09"));
    final ApiCallback trainListApiCallback = new ApiCallback() {
        @Override
        public void onSuccess(Object object) {
            TrainList trainList = (TrainList) object;
            List<TrainInfo> trainInfoList = new ArrayList<>();
            for (TrainInfo trainInfo : trainList.getTrainInfoItem().get(0).getTrainInfoItemList().get(0).getTrainInfoBodyList()) {
                if (trainFilterSet.contains(trainInfo.getVehicleId())) {
                    trainInfoList.add(trainInfo);
                }
            }
            trainInfoView.showTrainList(trainInfoList);
            trainInfoView.hideLoading();
            getTrainStationList();
        }

        @Override
        public void onFailure(Throwable throwable) {
            trainInfoView.showMessage(R.string.train_info_fail);
            trainInfoView.hideLoading();
        }
    };
    public TrainInfoFragmentPresenter(TrainInfoFragmentContract.View trainInfoView, TrainInteractor trainInteractor) {
        this.trainInfoView = trainInfoView;
        this.trainInteractor = trainInteractor;
        trainInfoView.setPresenter(this);
    }

    public void getTrainList() {
        trainInfoView.showLoading();
        trainInteractor.getTrainList(trainListApiCallback);
    }

    public void getTrainCityList() {
        trainInfoView.showLoading();
        trainInteractor.getTrainCityList(trainCityListApiCallback);
    }

    public void getTrainStationList() {
        trainInfoView.showLoading();
        trainInteractor.getTrainStationList(trainStationListApiCallback);
    }

    public void getTrainSearchList(String depPlaceId, String arrPlaceId, String depPlandTime, String trainGradeCode) {
        trainInfoView.showLoading();
        trainInteractor.getTrainSearchList(depPlaceId, arrPlaceId, depPlandTime, trainGradeCode, trainSearchListApiCallback);
    }
}
