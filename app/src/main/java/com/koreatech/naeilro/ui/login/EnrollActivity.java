package com.koreatech.naeilro.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.entity.enroll.EnrollObject;
import com.koreatech.naeilro.network.interactor.EnrollRestInteractor;

public class EnrollActivity extends AppCompatActivity implements View.OnClickListener, EnrollActivityContract.View {
    private EditText idEditText;
    private EditText passwordEditText;
    private EditText emailEditText;
    private Button completeButton;
    private EnrollActivityPresenter enrollActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);
        init();
    }
    protected void init(){
        idEditText = (EditText) findViewById(R.id.id_edit);
        passwordEditText = (EditText) findViewById(R.id.password_edit);
        emailEditText = (EditText) findViewById(R.id.email_edit);
        completeButton = (Button) findViewById(R.id.complete_button);
        enrollActivityPresenter = new EnrollActivityPresenter(new EnrollRestInteractor(), this);
        completeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.complete_button){
            String name = idEditText.getText().toString();
            String pw = passwordEditText.getText().toString();
            String email = emailEditText.getText().toString();
            enrollActivityPresenter.getEnrollResult(name, email, pw);



        }
    }

    @Override
    public void showEnrollResult(EnrollObject enrollObject) {
        int result = enrollObject.getSuccess();
        setMessage(result);
    }
    public void setMessage(int result){
        if(result == 1) {
            Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "양식을 다시한번 확인해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setPresenter(EnrollActivityPresenter presenter) {
        this.enrollActivityPresenter = presenter;
    }
}
