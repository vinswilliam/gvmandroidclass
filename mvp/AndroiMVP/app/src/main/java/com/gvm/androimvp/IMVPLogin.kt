package com.gvm.androimvp


interface IMVPLoginView {
    fun showLoginIndicator()
    fun showHideIndicator()
    fun enableLoginBtn()
    fun disableLoginBtn()
    fun showUsernameTooShort()
    fun showPasswordTooShort()
    fun showLoginSuccess()
}

interface IMVPLoginPresenter {
    fun onAttach(view: IMVPLoginView)
    fun onDetach()
    fun onLogin(username: String, password: String)
}