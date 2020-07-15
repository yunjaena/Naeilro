package com.koreatech.naeilro.network.interactor;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.core.network.KoreanTourRetrofitManager;
import com.koreatech.naeilro.NaeilroApplication;
import com.koreatech.naeilro.network.entity.restaurant.RestaurantInfo;
import com.koreatech.naeilro.network.entity.restaurant.RestaurantList;
import com.koreatech.naeilro.network.service.RestaurantService;
import com.koreatech.naeilro.util.DataAPIMessageUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RestaurantRestInteractor implements RestaurantInteractor {

    @Override
    public void searchRestaurantInfo(String searchText, ApiCallback apiCallback) {
        String apiKey = NaeilroApplication.getDataApiKey();
        List<RestaurantInfo> restaurantInfoList = new ArrayList<>();
        Observable<RestaurantList> restaurantListObservable = KoreanTourRetrofitManager.getInstance().getRetrofit().create(RestaurantService.class).
                getRestaurantSearch(apiKey, 100, 1, "AND", "Naeilro", "A", "Y", 39, searchText);

        restaurantListObservable.map(restaurantList -> {
            if (!DataAPIMessageUtil.isSuccess(restaurantList.getMessageList().get(0))) {
                throw new Exception("Can not get restaurant Data");
            }
            return restaurantList;
        }).flatMap(restaurantList -> {
            int totalCount = restaurantList.getRestaurantInfoBodyList().get(0).getTotalCount();
            int numOfRows = restaurantList.getRestaurantInfoBodyList().get(0).getNumOfRows();
            int totalPage = (int) Math.ceil((double) totalCount / numOfRows) + 1;
            List<Observable<RestaurantList>> restaurantObservableList = new ArrayList<>();
            for (int i = 1; i < totalPage; i++) {
                restaurantObservableList.add(KoreanTourRetrofitManager.getInstance().getRetrofit().create(RestaurantService.class).getRestaurantSearch(apiKey, 10, i, "AND", "Naeilro", "A", "Y", 39, searchText));
            }
            return Observable.concat(restaurantObservableList);
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RestaurantList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RestaurantList restaurantList) {
                        restaurantInfoList.addAll(restaurantList.getRestaurantInfoBodyList().get(0).getRestaurantInfoItemList().get(0).getRestaurantInfoList());
                    }

                    @Override
                    public void onError(Throwable e) {
                        apiCallback.onFailure(new Throwable("Can not get restaurant info"));
                    }

                    @Override
                    public void onComplete() {
                        apiCallback.onSuccess(restaurantInfoList);
                    }
                });
    }
}
