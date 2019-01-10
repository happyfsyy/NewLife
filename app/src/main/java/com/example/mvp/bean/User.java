package com.example.mvp.bean;

/**
 * 架构更改为MVP以后，Presenter的出现，将Activity视为View层，Presenter层负责完成View层和Model层的交互。
 * <li>View对应于Activity，负责View的绘制以及和用户交互。
 * <li>Model应该是业务逻辑和实体模型。
 * <li>Presenter负责完成View和Model的交互。
 */
public class User {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
