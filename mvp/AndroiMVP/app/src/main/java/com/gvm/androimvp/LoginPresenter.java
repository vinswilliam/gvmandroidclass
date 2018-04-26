package com.gvm.androimvp;

import android.os.Handler;

import org.jetbrains.annotations.NotNull;

public class LoginPresenter
        implements IMVPLoginPresenter {

    private IMVPLoginView mView;
    private Consumer mConsumer = new Consumer();

    @Override
    public void onAttach(@NotNull IMVPLoginView view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    @Override
    public void onLogin(@NotNull String username,
                        @NotNull String password) {
        mConsumer.setUsername(username);
        mConsumer.setPassword(password);

        mView.showLoginIndicator();
        mView.disableLoginBtn();
        new Handler().postDelayed(() -> {
            if (mConsumer.getUsername().length() < 6) {
                mView.showUsernameTooShort();
            } else if (mConsumer.getPassword().length() < 6) {
                mView.showPasswordTooShort();
            } else {
                mView.showLoginSuccess();
            }
            mView.showHideIndicator();
            mView.enableLoginBtn();
        },2000);
    }
}
