package com.koreatech.naeilro.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.koreatech.core.activity.ActivityBase;
import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.ui.login.LoginActivity;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.splash.presenter.SplashContract;
import com.koreatech.naeilro.ui.splash.presenter.SplashPresenter;

public class SplashActivity extends ActivityBase implements SplashContract.View {
    private SplashPresenter splashPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    private void init() {
        splashPresenter = new SplashPresenter(this);
        new Handler().postDelayed(() -> splashPresenter.loginCheck(), 2000);
    }


    @Override
    public void showMessage(String message) {
        ToastUtil.getInstance().makeShort(message);
    }

    @Override
    public void showMessage(int message) {
        ToastUtil.getInstance().makeShort(message);
    }

    @Override
    public void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        ToastUtil.getInstance().makeShort(R.string.auto_login);
        finish();
    }

    @Override
    public void setPresenter(SplashPresenter presenter) {
        this.splashPresenter = presenter;
    }
}
