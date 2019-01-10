package com.example.mvp.biz;

import com.example.mvp.bean.User;

public class UserBiz implements IUserBiz{
    @Override
    public void login(final String username, final String password, final OnLoginListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                if(username.equals("ale")&&password.equals("123")){
                    User user=new User();
                    user.setPassword(password);
                    user.setUsername(username);
                    listener.loginSuccess(user);
                }else{
                    listener.loginFailed();
                }
            }
        }).start();
    }
}
