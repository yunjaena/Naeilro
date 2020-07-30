package com.koreatech.naeilro.network.interactor;

import com.koreatech.core.network.ApiCallback;

public interface UserInteractor {
    void signUp(ApiCallback apiCallback, String name, String pw, String email);

    void signIn(ApiCallback apiCallback, String email, String password);
}
