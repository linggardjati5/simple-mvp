package com.example.simple_mvp.presenter;

import com.example.simple_mvp.LoginView;

public class LoginPresenter implements com.example.simple_mvp.presenter.LoginInteractor.OnLoginFinishedListener {

    private LoginView loginView;
    private com.example.simple_mvp.presenter.LoginInteractor loginInteractor;

    public LoginPresenter(LoginView loginView, com.example.simple_mvp.presenter.LoginInteractor loginInteractor) {

        this.loginInteractor = loginInteractor;
        this.loginView = loginView;
    }

    @Override
    public void onLoginError() {

        if (loginView!=null)
        {
            loginView.onLoginError();
        }
    }

    public void validateCredentials(String username, String password)
    {
        if (loginView!=null)
        {
            loginInteractor.canLogin(username,password,this);
        }
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
        }
    }

    @Override
    public void onPasswordError() {

        if (loginView != null) {
            loginView.setPasswordError();
        }

    }

    @Override
    public void onLoginSuccess(String username) {

        if (loginView != null) {
            loginView.onLoginSuccess(username);
        }

    }
}