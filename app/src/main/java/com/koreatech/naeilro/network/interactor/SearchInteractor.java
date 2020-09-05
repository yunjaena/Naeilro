package com.koreatech.naeilro.network.interactor;

import com.koreatech.core.network.ApiCallback;

public interface SearchInteractor {
    void getSearchContentByKeyWord(ApiCallback apiCallback, int contentType, String keyWord, int page);
}
