package com.example.simple_mvp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simple_mvp.presenter.LoginInteractor;
import com.example.simple_mvp.presenter.LoginPresenter;

public class MainActivity extends AppCompatActivity implements LoginView {


    EditText edt_username, edt_password;
    ProgressBar progressbar;

    LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        loginPresenter = new LoginPresenter(this, new LoginInteractor());
    }

    void initViews() {
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        progressbar = findViewById(R.id.progressbar);
    }

    public void loginMe(View view) {
        showProgressbar();

        loginPresenter.validateCredentials(edt_username.getText().toString().trim(), edt_password.getText().toString().trim());
    }



    @Override
    public void onLoginSuccess(String username) {


        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);

    }

    @Override
    public void onLoginError() {
        hideProgressbar();

        Toast.makeText(this, "username or password doesn't match", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setUsernameError() {
        hideProgressbar();
        edt_username.setError("Username can't be empty!");
    }

    @Override
    public void setPasswordError() {
        hideProgressbar();
        edt_password.setError("password can't be empty!");
    }

    @Override
    public void showProgressbar() {

        progressbar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressbar() {

        progressbar.setVisibility(View.GONE);

    }
}