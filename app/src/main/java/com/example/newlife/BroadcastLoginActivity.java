package com.example.newlife;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class BroadcastLoginActivity extends BaseActivity{
    private EditText account;
    private EditText password;
    private Button login;
    private CheckBox rememberPwd;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private boolean isRememberPwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boradcast_login);

        account=findViewById(R.id.login_account);
        password=findViewById(R.id.login_password);
        login=findViewById(R.id.login_button);
        rememberPwd=findViewById(R.id.remember_password);

        preferences=getPreferences(Context.MODE_PRIVATE);
        isRememberPwd=preferences.getBoolean("isRememberPwd",false);
        if(isRememberPwd){
            String accountText=preferences.getString("accountText","");
            String passwordText=preferences.getString("passwordText","");
            account.setText(accountText);
            password.setText(passwordText);
            rememberPwd.setChecked(true);
        }

        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountText=account.getText().toString();
                String passwordText=password.getText().toString();
                if(accountText.equals("ale246")&&passwordText.equals("123456")){
                    SharedPreferences.Editor editor=preferences.edit();
                    if(rememberPwd.isChecked()){
                        editor.putBoolean("isRememberPwd",true);
                        editor.putString("accountText",accountText);
                        editor.putString("passwordText",passwordText);
                    }else{
                        editor.clear();
                    }
                    editor.apply();

                    Intent intent=new Intent(BroadcastLoginActivity.this,ForceOffLineAct.class);
                    startActivity(intent);
                }else{
                    account.setText("");
                    password.setText("");
                }
            }
        });
    }
}

