package com.example.mvp.view;

import com.example.mvp.bean.User;

public interface IUserLoginView {
    String getUsername();
    String getPassword();
    void clearUsername();
    void clearPassword();
    void showLoading();
    void hideLoading();
    void toMainActivity(User user);
    void showFailedError();
}
