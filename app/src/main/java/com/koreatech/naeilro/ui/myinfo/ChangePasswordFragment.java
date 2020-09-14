package com.koreatech.naeilro.ui.myinfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.koreatech.core.fragment.BaseFragment;
import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.auth.JWTTokenManager;
import com.koreatech.naeilro.network.interactor.UserRestInteractor;
import com.koreatech.naeilro.ui.login.LoginActivity;
import com.koreatech.naeilro.ui.main.MainActivity;
import com.koreatech.naeilro.ui.myinfo.presenter.ChangePasswordContract;
import com.koreatech.naeilro.ui.myinfo.presenter.ChangePasswordPresenter;
import com.koreatech.naeilro.util.FilterUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordFragment extends BaseFragment implements ChangePasswordContract.View {
    private View view;
    private String name;
    private String email;
    private TextView nameTextView;
    private TextView emailTextView;
    private TextInputLayout passwordTextInputLayout;
    private TextInputEditText passwordTextInputEditText;
    private TextInputLayout passwordCheckTextInputLayout;
    private TextInputEditText passwordCheckTextInputEditText;
    private Button changePasswordButton;
    private ChangePasswordPresenter changePasswordPresenter;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_change_password, container, false);
        name = getArguments().getString("name");
        email = getArguments().getString("email");
        ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    private void init(View view){
        changePasswordPresenter = new ChangePasswordPresenter(this, new UserRestInteractor());
        navController =  Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        initView(view);
    }

    private void initView(View view) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        nameTextView = view.findViewById(R.id.user_name_text_view);
        emailTextView = view.findViewById(R.id.user_email_text_view);
        passwordTextInputLayout = view.findViewById(R.id.user_password_text_input_layout);
        passwordTextInputEditText = view.findViewById(R.id.user_password_text_input_edit_text);
        passwordCheckTextInputLayout = view.findViewById(R.id.user_password_check_text_input_layout);
        passwordCheckTextInputEditText = view.findViewById(R.id.user_password_check_text_edit_text);
        changePasswordButton = view.findViewById(R.id.change_password_button);
        nameTextView.setText(name);
        emailTextView.setText(email);
    }


    private boolean isPasswordFormatValidate() {
        String password = passwordTextInputEditText.getText().toString();
        String passwordCheck = passwordCheckTextInputEditText.getText().toString();
        if (FilterUtil.isPasswordValidate(password) && password.length() >= 8) {
            passwordTextInputLayout.setError(null);
        } else {
            passwordTextInputLayout.setError("비밀번호 형식을 확인해주세요(8자리 이상)");
            return false;
        }

        if (password.equals(passwordCheck)) {
            passwordCheckTextInputLayout.setError(null);
        } else {
            passwordCheckTextInputLayout.setError("비밀번호가 서로 다릅니다.");
            return false;
        }

        return true;
    }

    @OnClick(R.id.change_password_button)
    public void onClickedChangePasswordButton(View view) {
        hideKeyBoard();
        if (isPasswordFormatValidate()) {
            String password = passwordTextInputEditText.getText().toString();
            changePasswordPresenter.changePassword(email,password);
        }
    }

    @Override
    public void showLoading() {
        ((MainActivity)getActivity()).showProgressDialog(R.string.loading);
    }

    @Override
    public void hideLoading() {
        ((MainActivity)getActivity()).hideProgressDialog();
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
    public void showChangePasswordSuccess() {
        navController.popBackStack();
    }

    @Override
    public void setPresenter(ChangePasswordPresenter presenter) {
        this.changePasswordPresenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
    }

    private void hideKeyBoard(){
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
