package com.koreatech.naeilro.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.koreatech.naeilro.R;
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginButton;
    private Button enrollButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        enrollButton = (Button) findViewById(R.id.enroll_button);
        enrollButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.enroll_button){
            Intent intent = new Intent(getApplicationContext(), EnrollActivity.class);
            startActivity(intent);

        }
    }
}
