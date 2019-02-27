package com.example.newlife;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditTestAct extends AppCompatActivity {
    private LinearLayout mainLayout;
    private EditText editText;
    private EditText subEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLayout= (LinearLayout)LayoutInflater.from(this).inflate(R.layout.activity_edit_test,null);
        setContentView(mainLayout);
        initViews();

    }
    private void initViews(){
        editText=mainLayout.findViewById(R.id.edit_text);
//        editText.requestFocus();
//        Timer timer=new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                popupKeyboard();
//            }
//        },1000);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_DEL&&event.getAction()==KeyEvent.ACTION_DOWN){
                    int cursorIndex=editText.getSelectionStart();
                    if(cursorIndex==0){
//                        removeImageView();
                        //todo
//                        LinearLayout linearLayout=(LinearLayout)mainLayout.getChildAt(0);
                        subEditText.requestFocus();

                    }
                }
                return false;
            }
        });
        subEditText=findViewById(R.id.sub_edit_text);
        subEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subEditText.requestFocus();
            }
        });
    }
    private void removeImageView(){
        mainLayout.removeViewAt(0);
    }
    private void popupKeyboard(){
        InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(0,InputMethodManager.SHOW_FORCED);
        imm.showSoftInput(editText,0);
    }
    private void hideKeyboard(){
        InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getWindow().getDecorView().getWindowToken(),0);
    }
    private void hideKeyboard2(){
        InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
