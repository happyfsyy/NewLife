package com.example.mvp.biz;

public interface IUserBiz {
    void login(String username,String password,OnLoginListener listener);
}
