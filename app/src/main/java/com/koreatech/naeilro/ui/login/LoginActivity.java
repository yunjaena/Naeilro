package com.koreatech.naeilro.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.koreatech.core.activity.ActivityBase;
import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.interactor.UserRestInteractor;
import com.koreatech.naeilro.ui.login.presenter.LoginContract;
import com.koreatech.naeilro.ui.login.presenter.LoginPresenter;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.util.FilterUtil;

public class LoginActivity extends ActivityBase implements View.OnClickListener, LoginContract.View {
    private Button loginButton;
    private Button enrollButton;
    private TextInputEditText emailTextInputEditText;
    private TextInputEditText passwordTextInputEditText;
    private TextInputLayout emailTextInputLayout;
    private TextInputLayout passwordTextInputLayout;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        enrollButton = findViewById(R.id.enroll_button);
        loginButton = findViewById(R.id.logint_button);
        emailTextInputLayout = findViewById(R.id.login_email_text_input_layout);
        passwordTextInputLayout = findViewById(R.id.login_password_text_input_layout);
        emailTextInputEditText = findViewById(R.id.login_email_text_input_edit_text);
        passwordTextInputEditText = findViewById(R.id.login_password_text_input_edit_text);
        loginPresenter = new LoginPresenter(this, new UserRestInteractor());
        enrollButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enroll_button:
                Intent intent = new Intent(getApplicationContext(), EnrollActivity.class);
                startActivity(intent);
                break;
            case R.id.logint_button:
                onClickedLoginButton();
                break;
        }
    }

    public void onClickedLoginButton() {
        if (!isLoginFormatCorrect()) {
            return;
        }
        String email = emailTextInputEditText.getText().toString();
        String password = passwordTextInputEditText.getText().toString();
        loginPresenter.login(email, password);
    }

    private boolean isLoginFormatCorrect() {
        return isEmailFormatValidate() && isPasswordFormatValidate();
    }

    private boolean isEmailFormatValidate() {
        String email = emailTextInputEditText.getText().toString();
        if (FilterUtil.isEmailValidate(email)) {
            emailTextInputLayout.setError(null);
        } else {
            emailTextInputLayout.setError("이메일 형식을 확인해주세요");
            return false;
        }
        return true;
    }

    private boolean isPasswordFormatValidate() {
        String password = passwordTextInputEditText.getText().toString();
        if (FilterUtil.isPasswordValidate(password)) {
            passwordTextInputLayout.setError(null);
        } else {
            passwordTextInputLayout.setError("비밀번호 형식을 확인해주세요");
            return false;
        }
        return true;
    }

    @Override
    public void showLoading() {
        showProgressDialog(R.string.loading_station_info);
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
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
    public void successLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void setPresenter(LoginPresenter presenter) {
        this.loginPresenter = presenter;
    }
}
