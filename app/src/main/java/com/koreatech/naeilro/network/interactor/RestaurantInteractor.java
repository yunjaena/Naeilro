package com.koreatech.naeilro.network.interactor;

import com.koreatech.core.network.ApiCallback;

public interface RestaurantInteractor {
    void searchRestaurantInfo(String searchText, ApiCallback apiCallback);
}
