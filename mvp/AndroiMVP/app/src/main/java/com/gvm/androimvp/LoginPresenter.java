package com.gvm.androimvp;

public class LoginPresenter implements IMVPLoginPresenter {

    private IMVPLoginView mView;

    @Override
    public void onAttach(IMVPLoginView view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    @Override
    public void onLogin(String username, String password) {
        Consumer mConsumer = new Consumer();
        mConsumer.setUsername(username);
        mConsumer.setPassword(password);

        mView.showLoginIndicator();
        mView.disableLoginBtn();

        if (mConsumer.getUsername().length() < 6
                && mConsumer.getUsername().length() > 0) {
            mView.showUsernameTooShort();
        } else if (mConsumer.getPassword().length() < 6
                && mConsumer.getPassword().length() > 0) {
            mView.showPasswordTooShort();
        } else if (mConsumer.getUsername().length() > 0
                && mConsumer.getPassword().length() > 0){
            mView.showLoginSuccess();
        } else {
            mView.showWarningEmptyUsernameAndPassword();
        }

        mView.hideIndicator();
        mView.enableLoginBtn();
    }
}
