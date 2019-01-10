package com.example.mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvp.bean.User;
import com.example.mvp.presenter.UserLoginPresenter;
import com.example.mvp.view.IUserLoginView;
import com.example.newlife.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserLoginActivity extends AppCompatActivity implements IUserLoginView {
    private EditText username,password;
    private Button login, clear;
    private ProgressBar progressBar;
    private UserLoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_login);
        initViews();
    }
    private void initViews(){
        username=findViewById(R.id.mvp_username);
        password=findViewById(R.id.mvp_password);
        login=findViewById(R.id.mvp_login);
        clear=findViewById(R.id.mvp_clear);
        progressBar=findViewById(R.id.mvp_progressbar);
        presenter=new UserLoginPresenter(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clear();
            }
        });
    }

    @Override
    public String getUsername() {
        return username.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void clearUsername() {
        username.setText("");
    }

    @Override
    public void clearPassword() {
        password.setText("");
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this,"to mainActivity",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,"login error",Toast.LENGTH_SHORT).show();
    }
}
