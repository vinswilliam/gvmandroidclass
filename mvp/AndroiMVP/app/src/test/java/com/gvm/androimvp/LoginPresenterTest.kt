package com.gvm.androimvp

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.verify
import org.mockito.Mockito.never


@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest {
    @Mock
    private lateinit var mMockView: IMVPLoginView

    private lateinit var mPresenter: LoginPresenter

    @Before
    fun setup() {
        mPresenter = LoginPresenter()
        mPresenter.onAttach(mMockView)
    }

    @Test
    fun testOnLoginSuccess() {
        val username = "burgerKeju123"
        val pwd = "123456"
        mPresenter.onLogin(username, pwd)

        verify(mMockView).showLoginIndicator()
        verify(mMockView).disableLoginBtn()
        verify(mMockView, never()).showUsernameTooShort()
        verify(mMockView, never()).showPasswordTooShort()
        verify(mMockView).showLoginSuccess()
        verify(mMockView).hideIndicator()
        verify(mMockView).enableLoginBtn()
    }

    @Test
    fun testOnLoginInvalidUsername() {
        val username = "burge"
        val pwd = "123456"
        mPresenter.onLogin(username, pwd)

        verify(mMockView).showLoginIndicator()
        verify(mMockView).disableLoginBtn()
        verify(mMockView).showUsernameTooShort()
        verify(mMockView, never()).showPasswordTooShort()
        verify(mMockView, never()).showLoginSuccess()
        verify(mMockView).hideIndicator()
        verify(mMockView).enableLoginBtn()
    }

    @Test
    fun testOnLoginInvalidPassword() {
        val username = "burger123456"
        val pwd = "123"
        mPresenter.onLogin(username, pwd)

        verify(mMockView).showLoginIndicator()
        verify(mMockView).disableLoginBtn()
        verify(mMockView, never()).showUsernameTooShort()
        verify(mMockView).showPasswordTooShort()
        verify(mMockView, never()).showLoginSuccess()
        verify(mMockView).hideIndicator()
        verify(mMockView).enableLoginBtn()
    }

    @Test
    fun testOnLoginEmptyUsername() {
        val username = ""
        val pwd = ""
        mPresenter.onLogin(username, pwd)

        verify(mMockView).showLoginIndicator()
        verify(mMockView).disableLoginBtn()
        verify(mMockView, never()).showUsernameTooShort()
        verify(mMockView, never()).showPasswordTooShort()
        verify(mMockView, never()).showLoginSuccess()
        verify(mMockView).hideIndicator()
        verify(mMockView).enableLoginBtn()
        verify(mMockView).showWarningEmptyUsernameAndPassword()
    }

    @After
    fun tearDown() {
        mPresenter.onDetach()
    }
}