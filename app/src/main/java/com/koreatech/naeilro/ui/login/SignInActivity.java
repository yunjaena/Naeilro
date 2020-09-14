package com.koreatech.naeilro.ui.login;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.koreatech.core.activity.ActivityBase;
import com.koreatech.core.toast.ToastUtil;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.user.EnrollObject;
import com.koreatech.naeilro.network.interactor.UserRestInteractor;
import com.koreatech.naeilro.ui.login.presenter.SignInActivityContract;
import com.koreatech.naeilro.ui.login.presenter.SignInPresenter;
import com.koreatech.naeilro.util.FilterUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SignInActivity extends ActivityBase implements View.OnClickListener, SignInActivityContract.View {
    private Button signInButton;
    private TextInputEditText nameTextInputEditText;
    private TextInputEditText emailTextInputEditText;
    private TextInputEditText passwordTextInputEditText;
    private TextInputEditText phoneNumberInputEditText;
    private TextInputLayout nameTextInputLayout;
    private TextInputLayout emailTextInputLayout;
    private TextInputLayout passwordTextInputLayout;
    private TextInputLayout phoneNumberTextInputLayout;
    private TextView personalInfoTermsTextView;
    private CheckBox personalInfoCheckBox;
    private SignInPresenter signInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();
    }

    protected void init() {
        signInButton = findViewById(R.id.sign_in_button);
        nameTextInputLayout = findViewById(R.id.signin_name_text_input_layout);
        emailTextInputLayout = findViewById(R.id.signin_email_text_input_layout);
        passwordTextInputLayout = findViewById(R.id.signin_password_text_input_layout);
        phoneNumberTextInputLayout = findViewById(R.id.signin_phone_text_input_layout);
        nameTextInputEditText = findViewById(R.id.signin_name_text_input_edit_text);
        emailTextInputEditText = findViewById(R.id.signin_email_text_input_edit_text);
        passwordTextInputEditText = findViewById(R.id.signin_password_text_input_edit_text);
        phoneNumberInputEditText = findViewById(R.id.signin_phone_text_input_edit_text);
        personalInfoTermsTextView = findViewById(R.id.signup_textview_personal_info_terms);
        personalInfoCheckBox = findViewById(R.id.signup_check_box_personal_info_terms);
        personalInfoTermsTextView.setPaintFlags(personalInfoTermsTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        signInPresenter = new SignInPresenter(new UserRestInteractor(), this);
        signInButton.setOnClickListener(this);
        personalInfoTermsTextView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sign_in_button) {
            onClickedSingInButton();
        } else if (v.getId() == R.id.signup_textview_personal_info_terms) {
            makeContractDialog("개인정보 이용약관", "naeilro_contract.txt");
        }
    }

    private void onClickedSingInButton() {
        if (!isSigninFormatCorrect() || !isTermCheckBoxChecked()) {
            return;
        }

        String name = nameTextInputEditText.getText().toString();
        String email = emailTextInputEditText.getText().toString();
        String password = passwordTextInputEditText.getText().toString();
        String phoneNumber = phoneNumberInputEditText.getText().toString();
        signInPresenter.getSignInResult(name, email, password, phoneNumber);
    }

    private boolean isNickNameFormatValidate() {
        String name = nameTextInputEditText.getText().toString();
        if (FilterUtil.isNickNameValidate(name) && name.length() >= 2) {
            nameTextInputLayout.setError(null);
        } else {
            nameTextInputLayout.setError("이름 형식을 확인해주세요");
            return false;
        }

        return true;
    }

    private boolean isTermCheckBoxChecked() {
        boolean isTermChecked = personalInfoCheckBox.isChecked();
        if (!isTermChecked) {
            ToastUtil.getInstance().makeShort("이용약관에 동의해주세요.");
            return false;
        }
        return true;
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
        if (FilterUtil.isPasswordValidate(password) && password.length() >= 8) {
            passwordTextInputLayout.setError(null);
        } else {
            passwordTextInputLayout.setError("비밀번호 형식을 확인해주세요");
            return false;
        }
        return true;
    }

    private boolean isPhoneNumberFormatValidate() {
        String phoneNumber = phoneNumberInputEditText.getText().toString();
        if (FilterUtil.isPhoneValidate(phoneNumber)) {
            phoneNumberTextInputLayout.setError(null);
        } else {
            phoneNumberTextInputLayout.setError("핸드폰 형식을 확인해주세요");
            return false;
        }
        return true;
    }

    private boolean isSigninFormatCorrect() {
        return isEmailFormatValidate() && isPasswordFormatValidate() && isPhoneNumberFormatValidate() && isNickNameFormatValidate();
    }

    @Override
    public void showEnrollResult(EnrollObject enrollObject) {
        setMessage(enrollObject);
    }

    public void setMessage(EnrollObject result) {
        if (result.getSuccess() == 1) {
            Toast.makeText(this, "회원가입이 완료되었습니다. 등록된 이메일로 발송된 메일을 통해 인증을 진행해주세요.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void makeContractDialog(String title, String textFile) {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_terms, null, false);

        BufferedReader reader = null;

        TextView textViewTitle = dialogView.findViewById(R.id.dialog_terms_title);
        textViewTitle.setText(title);

        TextView textViewContent = dialogView.findViewById(R.id.dialog_terms_content);
        textViewContent.setMovementMethod(ScrollingMovementMethod.getInstance());

        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(textFile), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            StringBuilder content = new StringBuilder();
            while ((mLine = reader.readLine()) != null) {
                content.append(mLine).append('\n');
            }
            textViewContent.setText(content.toString());
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.ContractDialog);
        builder.setView(dialogView);
        builder.setPositiveButton("확인",
                (dialog, which) -> {

                });

        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showLoading() {
        showProgressDialog(R.string.loading);
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }

    @Override
    public void showFailure() {
        Toast.makeText(this, "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(SignInPresenter presenter) {
        this.signInPresenter = presenter;
    }
}
