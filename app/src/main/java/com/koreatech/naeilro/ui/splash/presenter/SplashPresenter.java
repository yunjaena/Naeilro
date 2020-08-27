package com.koreatech.naeilro.ui.splash.presenter;

import com.koreatech.naeilro.auth.JWTTokenManager;

public class SplashPresenter {
    private SplashContract.View splashView;

    public SplashPresenter(SplashContract.View splashView){
        this.splashView = splashView;
    }


    public void loginCheck(){
        if(!JWTTokenManager.getInstance().isAccessTokenExpired()){
            splashView.goToMain();
            return;
        }
        splashView.goToLogin();
    }

}
