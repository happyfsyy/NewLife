package com.example.mvp.biz;

import com.example.mvp.bean.User;

public interface OnLoginListener {
    void loginSuccess(User user);
    void loginFailed();
}
