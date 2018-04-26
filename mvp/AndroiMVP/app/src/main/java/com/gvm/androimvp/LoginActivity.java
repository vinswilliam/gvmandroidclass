package com.gvm.androimvp;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity
        implements IMVPLoginView {

    private IMVPLoginPresenter mPresenter;
    private EditText etUsername, etPassword;
    private ContentLoadingProgressBar loginProgress;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindView();
        mPresenter = new LoginPresenter();
        mPresenter.onAttach(this);
    }

    private void bindView() {
        etUsername = findViewById(R.id.login_username);
        etPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.login_btn);
        loginProgress = findViewById(R.id.login_progress);
        btnLogin.setOnClickListener(v ->
                mPresenter.onLogin(
                        etUsername.getText().toString(),
                        etPassword.getText().toString()));
    }

    @Override
    public void showLoginIndicator() {
        loginProgress.show();
    }

    @Override
    public void showHideIndicator() {
        loginProgress.hide();
    }

    @Override
    public void enableLoginBtn() {
        btnLogin.setEnabled(true);
    }

    @Override
    public void disableLoginBtn() {
        btnLogin.setEnabled(false);
    }

    @Override
    public void showUsernameTooShort() {
        Snackbar.make(
            findViewById(android.R.id.content),
            getString(R.string.username_too_short),
            Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showPasswordTooShort() {
        Snackbar.make(
                findViewById(android.R.id.content),
                getString(R.string.pwd_too_short),
                Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginSuccess() {
        Snackbar.make(
                findViewById(android.R.id.content),
                getString(R.string.login_success),
                Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }
}
