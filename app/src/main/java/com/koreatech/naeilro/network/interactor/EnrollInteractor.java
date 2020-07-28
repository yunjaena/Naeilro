package com.koreatech.naeilro.network.interactor;

import com.koreatech.core.network.ApiCallback;

public interface EnrollInteractor {
    void getAccept(ApiCallback apiCallback, String name, String pw, String email);


}
