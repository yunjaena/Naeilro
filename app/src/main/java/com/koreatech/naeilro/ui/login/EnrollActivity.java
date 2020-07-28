package com.koreatech.naeilro.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.koreatech.core.network.ApiCallback;
import com.koreatech.naeilro.R;
import com.koreatech.naeilro.network.interactor.EnrollRestInteractor;

public class EnrollActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText idEditText;
    private EditText passwordEditText;
    private EditText emailEditText;
    private Button completeButton;
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
        completeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.complete_button){
            ApiCallback apiCallback = new ApiCallback() {
                @Override
                public void onSuccess(Object object) {

                }

                @Override
                public void onFailure(Throwable throwable) {

                }
            };
            EnrollRestInteractor interactor = new EnrollRestInteractor();
            interactor.getAccept(apiCallback,idEditText.getText().toString(),passwordEditText.getText().toString(), emailEditText.getText().toString());
        }
    }
}
