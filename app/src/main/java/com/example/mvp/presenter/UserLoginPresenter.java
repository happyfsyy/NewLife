package com.example.mvp.presenter;

import android.os.Handler;

import com.example.mvp.bean.User;
import com.example.mvp.biz.IUserBiz;
import com.example.mvp.biz.OnLoginListener;
import com.example.mvp.biz.UserBiz;
import com.example.mvp.view.IUserLoginView;


public class UserLoginPresenter {
    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler=new Handler();

    public UserLoginPresenter(IUserLoginView iUserLoginView){
        this.userLoginView=iUserLoginView;
        userBiz=new UserBiz();
    }

    public void login(){
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUsername(), userLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });
            }
        });
    }

    public void clear(){
        userLoginView.clearUsername();
        userLoginView.clearPassword();
    }
}
